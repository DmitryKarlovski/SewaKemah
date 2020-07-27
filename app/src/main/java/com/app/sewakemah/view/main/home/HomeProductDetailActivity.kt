package com.app.sewakemah.view.main.home

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.sewakemah.R
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.data.listener.ProductListener
import com.app.sewakemah.view.main.MainActivity
import com.app.sewakemah.view.main.adapter.ProductAdapter
import com.app.sewakemah.view.main.cart.CartMainFragment
import com.app.sewakemah.view.main.liked.LikedMainFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.bottomsheet_product_add.*
import kotlinx.android.synthetic.main.bottomsheet_product_add.view.*
import kotlinx.android.synthetic.main.popup_add_to_cart_success.*
import kotlinx.android.synthetic.main.toolbar_detail_page.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class HomeProductDetailActivity : AppCompatActivity() {

    private var bottomSheet: BottomSheetDialog? = null
    private var mSelectedItem : Int? = null

    private var quant: String? = ""
    private var startDate: String? = ""
    private var endDate: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        initComponents()
    }

    private fun initComponents(){
        val product = intent.extras!!.get("data") as ProductEntity

        textView117.text = product?.productName
        textView119.text = product?.productDesc
        textView121.text = product?.productAddr
        textView122.text = product?.productType
        imageView47.setImageResource(product?.productImg)
        
        if(likeIcon.isChecked){
            Log.d("Like", "Bacot")
            val intent = Intent(this, LikedMainFragment::class.java)

            intent.putExtra("productName", textView117.text as String)
            intent.putExtra("productDesc", textView119.text as String)
            intent.putExtra("productAddr", textView121.text as String)
            intent.putExtra("productImg", product?.productImg)
            intent.putExtra("productPrice", product?.productPrice)
            intent.putExtra("productType", textView122.text as String)

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        } else {
            
        }

        var productAdapter = ProductAdapter(object :
            ProductListener {
            override fun onClick(product: ProductEntity) {
                TODO("Not yet implemented")

            }
        })

        rv_product_detail.apply {
            layoutManager = LinearLayoutManager(this@HomeProductDetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = productAdapter
        }

        button30.setOnClickListener {
            bottomSheetCustom()
        }

        home_card_product_back.setOnClickListener {
            onBackPressed()
        }

//        Store Detail
        imageView48
        textView122
        textView123
        button29

        bottomSheet = BottomSheetDialog(this)

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
            IntentFilter("custom-message"))


    }

    private fun bottomSheetCustom(){
        val view = layoutInflater.inflate(R.layout.bottomsheet_product_add, product_bs_add_cart)
        val product = intent.extras!!.get("data") as ProductEntity
        bottomSheet!!.setContentView(view)
        bottomSheet!!.show()

        view.imageView38.setImageResource(R.drawable.ic_close_black)
        view.textView90.text = "Add to Cart"
        val layoutParams = LinearLayout.LayoutParams(48, 48)
        view.imageView38.setLayoutParams(layoutParams)

        val dateFormatter = SimpleDateFormat("dd - MM - yyyy", Locale.US)

        view.editTextTextPersonName28.isFocusable = false
        view.editTextTextPersonName28.isClickable = true
        view.editTextTextPersonName28.isLongClickable = false

        view.editTextTextPersonName29.isFocusable = false
        view.editTextTextPersonName29.isClickable = true
        view.editTextTextPersonName29.isLongClickable = false

        if(product == null){
            view.textView163.text = "lol"
            view.textView164.text = "lol"
        } else {
            view.imageView60.setImageResource(product.productImg)
            view.textView163.text = product.productName
            view.textView164.text = "Rp " + NumberFormat.getNumberInstance(Locale.US).format(product.productPrice) + "/" + NumberFormat.getNumberInstance(Locale.US).format(product.productDays) + " Days"
        }

        view.imageView38.setOnClickListener {
            bottomSheet!!.dismiss()
        }

        view.editTextTextPersonName28.setOnClickListener {
            val newCalendar : Calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    val newDate : Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    view.editTextTextPersonName28.setText(dateFormatter.format(newDate.getTime()))

            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }

        view.editTextTextPersonName29.setOnClickListener {
            val newCalendar : Calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val newDate : Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                view.editTextTextPersonName29.setText(dateFormatter.format(newDate.getTime()))

            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }

        view.button35.setOnClickListener {
            val myDialog = Dialog(this@HomeProductDetailActivity)
            myDialog.setContentView(R.layout.popup_add_to_cart_success)

            quant = view.editTextQuantity.text.toString()
            startDate = view.editTextTextPersonName28.text.toString()
            endDate = view.editTextTextPersonName29.text.toString()

            myDialog.button24.setOnClickListener {
                myDialog.dismiss()
                bottomSheet!!.dismiss()
            }
            myDialog.button25.setOnClickListener {
                var homeItem: MenuItem? = null

                try {
                    homeItem = main_bottom_nav.getMenu().getItem(2)
                }catch (error: NullPointerException){
                    Toast.makeText(this,""+error+"", Toast.LENGTH_SHORT).show()
                }

                val bundle = Bundle()
                bundle.putString("QUANTITY", quant!!)
                bundle.putString("STARTDATE", startDate!!)
                bundle.putString("ENDDATE", endDate!!)
                // set Fragmentclass Arguments
                val fragobj = CartMainFragment()

                mSelectedItem = 0
                if (try {
                        mSelectedItem !== homeItem!!.getItemId()
                    }catch (error :KotlinNullPointerException){
                        Toast.makeText(this,""+error+"", Toast.LENGTH_SHORT).show()
                    } as Boolean
                ) {
                    //CartMainFragment(homeItem)

                    // Select home item
                    main_bottom_nav.setSelectedItemId(homeItem!!.getItemId())

                    //startActivity(Intent(this, MainActivity::class.java))
                    var tent: Intent? = Intent(this, MainActivity::class.java);
                    //tent?.putExtra("openF2",true)
                    overridePendingTransition(0, 0);
                    fragobj.arguments = bundle
                    tent?.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(tent);
                } else {
                    super.onBackPressed()
                }
            }

            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()
            val window = myDialog.window
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    var mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            // Get extra data included in the Intent
            val productImg = intent.getStringExtra("product_img")
            val productName = intent.getStringExtra("product_name")
//        imageView47.setImageResource(productImg)
//        textView117.text = productName
        }
    }
}

package com.app.sewakemah.view.main.profile

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.view.WelcomeScreenActivity
import com.app.sewakemah.view.main.profile.personalInfo.ProfilePersonalActivity
import com.app.sewakemah.view.main.profile.profilerenter.paymentmethod.ProfilePaymentMethodActivity
import com.app.sewakemah.view.main.profile.profilerenter.profilestep.ProfileStepActivity
import com.app.sewakemah.view.main.profile.profilerenter.rentedstuff.ProfileRentedActivity
import com.app.sewakemah.view.main.profile.profilerenter.securitysettings.ProfileSecurityActivity
import com.app.sewakemah.view.main.profile.profilerenter.waitingpayment.ProfileWaitingActivity
import com.app.sewakemah.view.main.profile.profilestore.deposit.StoreDepositActivity
import com.app.sewakemah.view.main.profile.profilestore.detail.StoreDetailActivity
import com.app.sewakemah.view.main.profile.profilestore.stuff.StoreStuffActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_store_register.*
import kotlinx.android.synthetic.main.bottomsheet_store_register.view.*
import kotlinx.android.synthetic.main.fragment_main_profile.*
import kotlinx.android.synthetic.main.toolbar_detail_page.view.*
import java.io.ByteArrayOutputStream

class ProfileMainFragment : Fragment() {
    private var bottomSheet: BottomSheetDialog? = null
    private var storeName: String? = ""
    private var storePhone: String? = ""
    private var storeAddress: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPage()
        initComponents()

        var photo = Preferences.getKeyUserPhoto(context)
        var userName = Preferences.getLoggedInUser(context)
        var storeName = Preferences.getKeyUserStore(context)
        var storePhoto = Preferences.getKeyUserStorePhoto(context)

        if(photo.isNotEmpty()){
            imageView17.setImageBitmap(decodeBase64(photo))
        }
        if(storePhoto.isNotEmpty()){
            imageView31.setImageBitmap(decodeBase64(storePhoto))
        }

        textView45.text = userName
        textView76.text = storeName

    }

    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(context)) {
            profile_layout_notlogin.visibility = View.GONE
            profile_layout_switch.visibility = View.VISIBLE
            button13.setBackgroundResource(R.drawable.bg_btn_blue_32dp_round)
            button14.setBackgroundResource(R.drawable.bg_btn_gray_32dp_round)
            profile_layout_renter.visibility = View.VISIBLE
            profile_layout_store.visibility = View.GONE
            profile_layout_notStore.visibility = View.GONE
        } else {
            profile_layout_notlogin.visibility = View.VISIBLE
            profile_layout_switch.visibility = View.GONE
            profile_layout_renter.visibility = View.GONE
            profile_layout_store.visibility = View.GONE
            profile_layout_notStore.visibility = View.GONE
        }
    }

    private fun viewPage(){
        //profile_layout_notlogin.visibility = View.GONE
        //profile_layout_switch.visibility = View.VISIBLE


        button13.setOnClickListener {
            button13.setBackgroundResource(R.drawable.bg_btn_blue_32dp_round)
            button14.setBackgroundResource(R.drawable.bg_btn_gray_32dp_round)
            profile_layout_renter.visibility = View.VISIBLE
            profile_layout_store.visibility = View.GONE
            profile_layout_notStore.visibility = View.GONE
        }

        button14.setOnClickListener {
            button13.setBackgroundResource(R.drawable.bg_btn_gray_32dp_round)
            button14.setBackgroundResource(R.drawable.bg_btn_blue_32dp_round)
            if(Preferences.getKeyUserStore(context) == ""){
                profile_layout_renter.visibility = View.GONE
                profile_layout_store.visibility = View.GONE
                profile_layout_notStore.visibility = View.VISIBLE
            }else{
                profile_layout_renter.visibility = View.GONE
                profile_layout_store.visibility = View.VISIBLE
                profile_layout_notStore.visibility = View.GONE
            }

        }

        buttonCreateStore.setOnClickListener(){
            bottomSheetCustom()
        }

        bottomSheet = BottomSheetDialog(context!!)

    }

    private fun bottomSheetCustom() {
        val view = layoutInflater.inflate(R.layout.bottomsheet_store_register, store_register_page)
//        val product = intent.extras!!.get("data") as ProductEntity
        bottomSheet!!.setContentView(view)
        bottomSheet!!.show()

        view.imageView38.setImageResource(R.drawable.ic_close_black)
        view.textView90.text = "Open My Own Store"

        val layoutParams = LinearLayout.LayoutParams(48, 48)
        view.imageView38.layoutParams = layoutParams

        view.imageView38.setOnClickListener {
            bottomSheet!!.dismiss()
        }

        view.button39.setOnClickListener(){
            //check runtime permission
            permissionPhoto()
        }

        view.button40.setOnClickListener {
            //Log.d("Like", "Bacot")
            storeName = view.editTextTextPersonName36?.text.toString()
            storePhone = view.editTextTextPersonName37?.text.toString()
            storeAddress = view.editTextTextPersonName38?.text.toString()
            view.editTextTextPersonName36?.error = null
            view.editTextTextPersonName37?.error = null
            view.editTextTextPersonName38?.error = null

            var cancel1 = false
            var cancel2 = false
            var cancel3 = false

            if(storeName!!.isEmpty()){
                view.editTextTextPersonName36?.error = "Username is Empty"
                cancel1 = false
            }else if(storeName!!.length < 8){
                view.editTextTextPersonName36?.error = "Username must be more than 8"
                cancel1 = false
            }else{
                view.editTextTextPersonName36?.error = null
                cancel1 = true
            }


            if(storePhone!!.isEmpty()) {
                view.editTextTextPersonName37?.error = "Phone Number is Empty"
                cancel2 = false
            }else if(storePhone!!.take(2) != "08"){
                view.editTextTextPersonName37?.error = "Phone Number must start with 08"
                cancel2 = false
            }else if(storePhone!!.length < 10){
                view.editTextTextPersonName37?.error = "Phone Number must be more than 10"
                cancel2 = false
            }else{
                view.editTextTextPersonName37?.error = null
                cancel2 = true
            }

            if(storeAddress!!.isEmpty()){
                view.editTextTextPersonName38?.error = "Address is Empty"
                cancel3 = false
            }else if(storeAddress!!.length > 150){
                view.editTextTextPersonName38.error = "Address must be more than 8"
                cancel3 = false
            }else{
                view.editTextTextPersonName38?.error = null
                cancel3 = true
            }

            if(cancel1 && cancel2 && cancel3){
                saveStoreName(storeName!!)
                saveStorePhone(storePhone!!)
                saveStoreAddress(storeAddress!!)

                val bitmap = (view.imageView58?.getDrawable() as BitmapDrawable).bitmap
                encodeTobase64(bitmap)?.let { saveStorePhoto(it) }

                bottomSheet!!.dismiss()
//            startActivity(Intent(this, ProfilePersonalActivity::class.java))
            }
        }
    }

    private fun permissionPhoto(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(context?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) }
                == PackageManager.PERMISSION_DENIED) {
                //permission denied
                val permiss = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                //show pop up to request runtime permission
                requestPermissions(permiss, permissionCode)
            }else {
                //permission granted
                launchGallery()
            }
        }else {
            //system OS <= Marshmallow
            launchGallery()
        }

    }

    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, imgCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            permissionCode -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchGallery()
                }else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        //val tampilan = layoutInflater.inflate(R.layout.bottomsheet_store_register, store_register_page)
//        //var gambar: ImageView? = null
//        //gambar = store_register_page.imageView58
//        if (resultCode == Activity.RESULT_OK && requestCode == imgCode) {
//            imageView58?.setImageURI(data?.data)
//            //gambar?.setImageURI(data?.data)
//        }
//
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        val tampilan = layoutInflater.inflate(R.layout.bottomsheet_store_register, store_register_page)
        if (resultCode == Activity.RESULT_OK && requestCode == imgCode) {
            tampilan.imageView58?.setImageURI(data?.data)
        }
    }

    private fun encodeTobase64(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        val imageEncoded: String = Base64.encodeToString(b, Base64.DEFAULT)
        Log.d("Image Log:", imageEncoded)
        return imageEncoded
    }

    private fun saveStoreName(value: String) {
        Preferences.setKeyUserStore(context, value)
    }
    private fun saveStorePhone(value: String) {
        Preferences.setKeyUserStorePhone(context, value)
    }
    private fun saveStoreAddress(value: String) {
        Preferences.setKeyUserStoreAddress(context, value)
    }
    private fun saveStorePhoto(value: String) {
        Preferences.setKeyUserStorePhoto(context, value)
    }


    private fun initComponents(){
//        Renter Page
        profile_layout_step.setOnClickListener {
            startActivity(Intent(context, ProfileStepActivity::class.java))
        }

        profile_layout_personal.setOnClickListener {
            startActivity(Intent(context, ProfilePersonalActivity::class.java))
        }

        profile_layout_payment.setOnClickListener {
            startActivity(Intent(context, ProfilePaymentMethodActivity::class.java))
        }

        profile_layout_security.setOnClickListener {
            startActivity(Intent(context, ProfileSecurityActivity::class.java))
        }

        profile_layout_waiting_pay.setOnClickListener {
            startActivity(Intent(context, ProfileWaitingActivity::class.java))
        }

        profile_layout_rented.setOnClickListener {
            startActivity(Intent(context, ProfileRentedActivity::class.java))
        }

        profile_layout_logout1.setOnClickListener {
            popupCustom()
        }

//        Store Page
        profile_layout_storeinfo.setOnClickListener {
            startActivity(Intent(context, StoreDetailActivity::class.java))
        }

        profile_layout_rentedprod.setOnClickListener {
            startActivity(Intent(context, StoreStuffActivity::class.java))
        }

        profile_layout_deposit.setOnClickListener {
            startActivity(Intent(context, StoreDepositActivity::class.java))
        }

        profile_layout_logout2.setOnClickListener {
            popupCustom()
        }
    }

    private fun popupCustom(){
        val myDialog = Dialog(requireContext())
        myDialog.setContentView(R.layout.popup_logout)

        val cancel = myDialog.findViewById(R.id.exit_btn2) as Button
        val exit = myDialog.findViewById(R.id.exit_btn) as Button

        cancel.setOnClickListener {
            myDialog.dismiss()
        }

        exit.setOnClickListener {
            myDialog.dismiss()
            //super.onBackPressed()
            Preferences.clearLoggedInUser(context)
            startActivity(Intent(context, WelcomeScreenActivity::class.java))
        }

        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
        val window = myDialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    companion object {
        fun newInstance(): ProfileMainFragment {
            val fragment = ProfileMainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
        //image pick code
        private const val imgCode = 1000
        //permission code
        private const val permissionCode = 1001
    }
}
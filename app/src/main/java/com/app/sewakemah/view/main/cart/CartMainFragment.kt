package com.app.sewakemah.view.main.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.view.login.LoginHomeActivity
import com.app.sewakemah.view.register.RegisterMainActivity
import kotlinx.android.synthetic.main.fragment_main_cart.*

class CartMainFragment : Fragment() {

    private var quant: String? = ""
    private var strdate: String? = ""
    private var enddate: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quant = arguments!!.getString("QUANTITY")
            strdate = arguments!!.getString("STARTDATE")
            enddate = arguments!!.getString("ENDDATE")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main_cart, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(context)) {
            cart_login.visibility = View.GONE
            cart_rv_products.visibility = View.VISIBLE
            layout_cart.visibility = View.VISIBLE
        } else if (quant != null) {
            if(quant!!.isNotEmpty()){
                cart_login.visibility = View.GONE
                cart_rv_products.visibility = View.VISIBLE
                layout_cart.visibility = View.GONE
            }else{
                cart_login.visibility = View.VISIBLE
                cart_rv_products.visibility = View.GONE
                layout_cart.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }


    private fun initComponents(){
        val listProducts = listOf(
            ProductEntity(
                productName = "Tas Camping Terbaik",
                productType = "Bromo Rent",
                productStore = "Bromo Rent",
                productAddr = "Jl. Bromokuy No.2, Jawa Tengah",
                productPrice = 300000,
                productDays = 3,
                productImg = R.drawable.camping_bag,
                productDesc = quant.toString())
        )

        button10.setOnClickListener {
            startActivity(Intent(context, LoginHomeActivity::class.java))
        }

        cart_layout_signup.setOnClickListener {
            startActivity(Intent(context, RegisterMainActivity::class.java))
        }

//        home_rv_products.apply {
//            layoutManager = GridLayoutManager(context, 2)
//            adapter = productAdapter
//        }
    }

    companion object {
        fun newInstance(): CartMainFragment {
            val fragment = CartMainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
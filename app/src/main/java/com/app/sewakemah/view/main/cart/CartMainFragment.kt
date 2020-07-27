package com.app.sewakemah.view.main.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.sewakemah.R
import com.app.sewakemah.data.CartEntity
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.data.listener.CartListener
import com.app.sewakemah.data.listener.ProductListener
import com.app.sewakemah.view.login.LoginHomeActivity
import com.app.sewakemah.view.main.adapter.CartAdapter
import com.app.sewakemah.view.main.adapter.ProductAdapter
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
            layout_cart.visibility = View.GONE
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
        var cartAdapter = CartAdapter(object :
            CartListener {
            override fun onClick(cart: CartEntity) {
                TODO("Not yet implemented")
            }
        })

        button10.setOnClickListener {
            startActivity(Intent(context, LoginHomeActivity::class.java))
        }

        cart_layout_signup.setOnClickListener {
            startActivity(Intent(context, RegisterMainActivity::class.java))
        }

        cart_rv_products.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = cartAdapter
        }

        button8.setOnClickListener {
            startActivity(Intent(context, PaymentSummaryActivity::class.java))
        }
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
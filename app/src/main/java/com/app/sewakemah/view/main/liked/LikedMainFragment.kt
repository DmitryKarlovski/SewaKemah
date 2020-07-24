package com.app.sewakemah.view.main.liked

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.view.login.LoginHomeActivity
import com.app.sewakemah.view.main.adapter.ProductAdapter
import com.app.sewakemah.data.listener.ProductListener
import com.app.sewakemah.view.register.RegisterMainActivity
import kotlinx.android.synthetic.main.fragment_main_liked.*

class LikedMainFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_liked, container, false)

    }

    override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(context)) {
            liked_layout_login.visibility = View.GONE
            liked_layout_notliked.visibility = View.VISIBLE
            liked_rv_products.visibility = View.GONE
        } else {
            liked_layout_login.visibility = View.VISIBLE
            liked_rv_products.visibility = View.GONE
            liked_layout_notliked.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()

    }


    private fun initComponents(){
        var likedAdapter = ProductAdapter(object :
            ProductListener {
            override fun onClick(product: ProductEntity) {
                TODO("Not yet implemented")
            }
        })

        liked_rv_products.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = likedAdapter
        }

        if(likedAdapter != null){
            liked_rv_products.visibility = View.VISIBLE
            liked_layout_login.visibility = View.GONE
            liked_layout_notliked.visibility = View.GONE
        }
        else {
            liked_rv_products.visibility = View.GONE
            liked_layout_login.visibility = View.GONE
            liked_layout_notliked.visibility = View.VISIBLE
        }



        button7.setOnClickListener {
            startActivity(Intent(context, LoginHomeActivity::class.java))
        }

        liked_layout_signup.setOnClickListener {
            startActivity(Intent(context, RegisterMainActivity::class.java))
        }

//        imageView9.setBackgroundResource(R.drawable.ic_like_enable)
    }

    companion object {
        fun newInstance(): LikedMainFragment {
            val fragment = LikedMainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
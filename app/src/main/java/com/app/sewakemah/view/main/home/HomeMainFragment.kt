package com.app.sewakemah.view.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.app.sewakemah.R
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.view.main.adapter.ProductAdapter
import com.app.sewakemah.data.listener.ProductListener
import kotlinx.android.synthetic.main.fragment_main_home.*

class HomeMainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()

    }


    private fun initComponents(){
        var productAdapter = ProductAdapter(object :
            ProductListener {
            override fun onClick(product: ProductEntity) {
                TODO("Not yet implemented")
            }
        })

        home_rv_products.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
        }
    }

    companion object {
        fun newInstance(): HomeMainFragment{
            val fragment = HomeMainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
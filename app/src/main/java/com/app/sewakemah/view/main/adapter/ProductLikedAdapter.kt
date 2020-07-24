package com.app.sewakemah.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.sewakemah.R
import com.app.sewakemah.data.ProductEntity
import kotlinx.android.synthetic.main.itemlist_home_product.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class ProductLikedAdapter (private val products: List<ProductEntity>) : RecyclerView.Adapter<ProductLikedHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProductLikedHolder {
        return ProductLikedHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.itemlist_home_product, viewGroup, false))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductLikedHolder, position: Int) {
        holder.bindProduct(products[position])
    }
}

class ProductLikedHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvProductName = view.textView11
    private val tvProductType = view.textView12
    private val tvProductAddr = view.textView14
    private val tvProductPrice = view.textView13
    private val tvProductImage = view.imageView8

    var formatter: DecimalFormat = DecimalFormat("#,###,###")

    fun bindProduct(product : ProductEntity) {
        tvProductName.text = product.productName
        tvProductType.text = product.productType
        tvProductAddr.text = product.productAddr
        tvProductPrice.setText("Rp " + NumberFormat.getNumberInstance(Locale.US).format(product.productPrice) + "/" + NumberFormat.getNumberInstance(Locale.US).format(product.productDays) + " Days")
        tvProductImage.setImageResource(product.productImg)

//        Integer.toString(product.productPrice)
//        Integer.toString(product.productDays)
    }
}
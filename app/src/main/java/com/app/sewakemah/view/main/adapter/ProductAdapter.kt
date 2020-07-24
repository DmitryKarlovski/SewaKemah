package com.app.sewakemah.view.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.sewakemah.R
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.data.listener.ProductListener
import com.app.sewakemah.view.main.home.HomeProductDetailActivity
import kotlinx.android.synthetic.main.itemlist_home_product.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class ProductAdapter(private val listener: ProductListener) : RecyclerView.Adapter<ProductHolder>() {

    val listProducts = listOf(
        ProductEntity(
            productName = "Tas Camping Terbaik",
            productType = "Bromo Rent",
            productStore = "Bromo Rent",
            productAddr = "Jl. Bromokuy No.2, Jawa Tengah",
            productPrice = 300000,
            productDays = 3,
            productImg = R.drawable.camping_bag,
            productDesc = "Bacot"),
        ProductEntity(
            productName = "Sleeping Bag Termurah",
            productType = "Bromo Camp Malang",
            productStore = "Bromo Camp Malang",
            productAddr = "Jl. Bromokuy No.2, Jawa Tengah",
            productPrice = 200000,
            productDays = 3,
            productImg = R.drawable.sleeping_bag,
            productDesc = "Bacot")

    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.itemlist_home_product, viewGroup, false), listener)
    }

    override fun getItemCount(): Int = listProducts.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bindProduct(position)
    }
}

class ProductHolder(view: View, private val listener: ProductListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private val tvProductName = view.textView11
    private val tvProductType = view.textView12
    private val tvProductAddr = view.textView14
    private val tvProductPrice = view.textView13
    private val tvProductImage = view.imageView8

    val listProducts = listOf(
        ProductEntity(
            productName = "Tas Camping Terbaik",
            productType = "Bromo Rent",
            productStore = "Bromo Rent",
            productAddr = "Jl. Bromokuy No.2, Jawa Tengah",
            productPrice = 300000,
            productDays = 3,
            productImg = R.drawable.camping_bag,
            productDesc = "Bacot"),
        ProductEntity(
            productName = "Sleeping Bag Termurah",
            productType = "Bromo Camp Malang",
            productStore = "Bromo Camp Malang",
            productAddr = "Jl. Bromokuy No.2, Jawa Tengah",
            productPrice = 200000,
            productDays = 3,
            productImg = R.drawable.sleeping_bag,
            productDesc = "Bacot")

    )

    override fun onClick(v: View?) {
        val intent = Intent(itemView.context, HomeProductDetailActivity::class.java)

        intent.putExtra("data", listProducts[adapterPosition])
//        intent.putExtra("productName", listProducts[adapterPosition].productName)
//        intent.putExtra("productDesc", listProducts[adapterPosition].productDesc)//you can name the keys whatever you like
//        intent.putExtra("productAddr", listProducts[adapterPosition].productAddr) //note that all these values have to be primitive (i.e boolean, int, double, String, etc.)
//        intent.putExtra("productImg", listProducts[adapterPosition].productImg)
//        intent.putExtra("productPrice", listProducts[adapterPosition].productPrice)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        itemView.context.startActivity(intent)
    }

    init {
        view.home_card_products.setOnClickListener(this)
    }

    var formatter: DecimalFormat = DecimalFormat("#,###,###")

    fun bindProduct(position: Int) {
        tvProductName.text = listProducts[position].productName
        tvProductType.text = listProducts[position].productType
        tvProductAddr.text = listProducts[position].productAddr
        tvProductPrice.setText("Rp " + NumberFormat.getNumberInstance(Locale.US).format(listProducts[position].productPrice) + "/" + NumberFormat.getNumberInstance(Locale.US).format(listProducts[position].productDays) + " Days")
        tvProductImage.setImageResource(listProducts[position].productImg)

// Integer.toString(product.productPrice)
// Integer.toString(product.productDays)
    }
}

package com.app.sewakemah.view.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.sewakemah.R
import com.app.sewakemah.data.CartEntity
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.data.listener.CartListener
import com.app.sewakemah.view.main.home.HomeProductDetailActivity
import kotlinx.android.synthetic.main.itemlist_cart_product.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class CartAdapter(private val listener: CartListener) : RecyclerView.Adapter<CartHolder>() {

    val listProducts = listOf(
        CartEntity(
            cartName = "Sleeping Bag Termurah",
            cartDays = "String",
            cartStart = "String",
            cartReturn = "String",
            cartSummary = "String",
            cartTotal = 300000,
            productImg = R.drawable.sleeping_bag),
        CartEntity(
            cartName = "Sleeping Bag Termurah",
            cartDays = "String",
            cartStart = "String",
            cartReturn = "String",
            cartSummary = "String",
            cartTotal = 300000,
            productImg = R.drawable.sleeping_bag)

    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CartHolder {
        return CartHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.itemlist_cart_product, viewGroup, false), listener)
    }

    override fun getItemCount(): Int = listProducts.size

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        holder.bindProduct(position)
    }
}

class CartHolder(view: View, private val listener: CartListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private val tvProductName = view.textView23
    private val tvProductDays = view.textView24
    private val tvProductStartDate = view.textView26
    private val tvProductReturnDate = view.textView28
    private val tvProductSummary = view.textView29
    private val tvProductTotal = view.textView31
    private val tvProductImage = view.imageView12

    val listCart = listOf(
        CartEntity(
            cartName = "Sleeping Bag Termurah",
            cartDays = "String",
            cartStart = "String",
            cartReturn = "String",
            cartSummary = "String",
            cartTotal = 300000,
            productImg = R.drawable.sleeping_bag),
        CartEntity(
            cartName = "Sleeping Bag Termurah",
            cartDays = "String",
            cartStart = "String",
            cartReturn = "String",
            cartSummary = "String",
            cartTotal = 300000,
            productImg = R.drawable.sleeping_bag)

    )

    val listProducts = listOf(
        ProductEntity(
            productName = "Sleeping Bag Termurah",
            productType = "Bromo Camp Malang",
            productStore = "Bromo Camp Malang",
            productAddr = "Jl. Bromokuy No.2, Jawa Tengah",
            productPrice = 200000,
            productDays = 3,
            productImg = R.drawable.sleeping_bag,
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
        intent.putExtra("cart", listCart[adapterPosition])
//        intent.putExtra("productName", listProducts[adapterPosition].productName)
//        intent.putExtra("productDesc", listProducts[adapterPosition].productDesc)//you can name the keys whatever you like
//        intent.putExtra("productAddr", listProducts[adapterPosition].productAddr) //note that all these values have to be primitive (i.e boolean, int, double, String, etc.)
//        intent.putExtra("productImg", listProducts[adapterPosition].productImg)
//        intent.putExtra("productPrice", listProducts[adapterPosition].productPrice)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        itemView.context.startActivity(intent)
    }

    init {
        view.cart_layout_products.setOnClickListener(this)
    }

    var formatter: DecimalFormat = DecimalFormat("#,###,###")

    fun bindProduct(position: Int) {
        tvProductName.text = listCart[position].cartName
        tvProductDays.text = listCart[position].cartDays
        tvProductStartDate.text = listCart[position].cartStart
        tvProductReturnDate.text = listCart[position].cartReturn
        tvProductSummary.text = listCart[position].cartSummary
        tvProductTotal.setText("Rp " + NumberFormat.getNumberInstance(Locale.US).format(listCart[position].cartTotal))
        tvProductImage.setImageResource(listCart[position].productImg)

// Integer.toString(product.productPrice)
// Integer.toString(product.productDays)
    }
}

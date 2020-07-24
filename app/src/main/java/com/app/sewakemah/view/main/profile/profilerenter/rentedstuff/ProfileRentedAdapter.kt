package com.app.sewakemah.view.main.profile.profilerenter.rentedstuff

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.sewakemah.R
import com.app.sewakemah.data.RentedEntity
import com.app.sewakemah.data.listener.RentedStuffListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_product_review.view.*
import kotlinx.android.synthetic.main.itemlist_rented_product.view.*
import kotlinx.android.synthetic.main.toolbar_detail_page.view.*

class ProfileRentedAdapter(private val rentedListener: RentedStuffListener) : RecyclerView.Adapter<ProfileRentedHolder>() {
    val listRented = listOf(
        RentedEntity(
            rentedName = "Tas Camping Terbaik",
            rentedType = "Bromo Rent",
            rentedAddr = "Jl. Bromokuy No.2, Jawa Tengah",
            rentedPrice = 300000,
            rentedDays = 3,
            rentedImg = R.drawable.camping_bag,
            rentedDesc = "Bacot",
            rentedTotal = 3600000)
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProfileRentedHolder {
        return ProfileRentedHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.itemlist_rented_product, viewGroup, false), rentedListener)
    }

    override fun getItemCount(): Int = listRented.size

    override fun onBindViewHolder(holder: ProfileRentedHolder, position: Int) {
        holder.bindRented(position)
    }
}

class ProfileRentedHolder(view: View, private val listener: RentedStuffListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private val tvProductName = view.textView96
    private val tvProductImage = view.imageView41
    private var bottomSheet: BottomSheetDialog? = null

    val listRented = listOf(
        RentedEntity(
            rentedName = "Tas Camping Terbaik",
            rentedType = "Bromo Rent",
            rentedAddr = "Jl. Bromokuy No.2, Jawa Tengah",
            rentedPrice = 300000,
            rentedDays = 3,
            rentedImg = R.drawable.camping_bag,
            rentedDesc = "Bacot",
            rentedTotal = 3600000)
    )

    override fun onClick(v: View?) {
//        val intent = Intent(itemView.context, ProfileRentedActivity::class.java)
//        intent.putExtra("rented_data", listRented[adapterPosition])
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
//        itemView.context.startActivity(intent)
        bottomSheetCustom()
    }

    private fun bottomSheetCustom(){
        bottomSheet = BottomSheetDialog(itemView.context)
        val layoutInflater = LayoutInflater.from(itemView.context);
        val view = layoutInflater.inflate(R.layout.bottomsheet_product_review, itemView.product_bs_review)
        val rented = listRented[adapterPosition]
        bottomSheet!!.setContentView(view)
        bottomSheet!!.show()

        view.imageView38.setImageResource(R.drawable.ic_close_black)
        view.textView90.text = "Stuff Review"
        val layoutParams = LinearLayout.LayoutParams(48, 48)
        view.imageView38.setLayoutParams(layoutParams)

        if(rented == null){
            view.imageView59.setImageResource(rented.rentedImg)
        } else {
            view.imageView59.setImageResource(rented.rentedImg)
        }

        view.imageView38.setOnClickListener {
            bottomSheet!!.dismiss()
        }
    }

    init {
        view.profile_card_rented.setOnClickListener(this)
    }

    fun bindRented(rented: Int) {
        tvProductName.text = listRented[adapterPosition].rentedName
        tvProductImage.setImageResource(listRented[adapterPosition].rentedImg)

//        Integer.toString(product.productPrice)
//        Integer.toString(product.productDays)
    }
}
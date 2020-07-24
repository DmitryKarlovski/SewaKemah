package com.app.sewakemah.view.main.profile.profilerenter.rentedstuff

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.sewakemah.R
import com.app.sewakemah.data.ProductEntity
import com.app.sewakemah.data.RentedEntity
import com.app.sewakemah.data.listener.RentedStuffListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_profile_rented.*
import kotlinx.android.synthetic.main.bottomsheet_product_review.*
import kotlinx.android.synthetic.main.bottomsheet_product_review.view.*
import kotlinx.android.synthetic.main.toolbar_detail_page.*
import kotlinx.android.synthetic.main.toolbar_detail_page.view.*
import java.text.NumberFormat
import java.util.*

class ProfileRentedActivity : AppCompatActivity() {

    private var bottomSheet: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_rented)

        initComponents()
    }

    private fun initComponents(){
        val profileRentedAdapter = ProfileRentedAdapter(object : RentedStuffListener{
            override fun onClick(product: RentedEntity) {
                TODO("Not yet implemented")
            }

        })

        profile_rv_rented_stuff.apply {
            layoutManager = LinearLayoutManager(this@ProfileRentedActivity)
            adapter = profileRentedAdapter
        }

        textView90.text = "Rented Stuff"
        imageView38.setOnClickListener {
            onBackPressed()
        }
    }
}
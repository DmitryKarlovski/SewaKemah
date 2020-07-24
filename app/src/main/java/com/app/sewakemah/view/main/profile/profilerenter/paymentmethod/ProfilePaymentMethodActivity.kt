package com.app.sewakemah.view.main.profile.profilerenter.paymentmethod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import kotlinx.android.synthetic.main.toolbar_detail_page.*

class ProfilePaymentMethodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_payment)

        initComponents()
    }

    private fun initComponents(){
        textView90.text = "Payment Method"
        imageView38.setOnClickListener {
            onBackPressed()
        }
    }
}
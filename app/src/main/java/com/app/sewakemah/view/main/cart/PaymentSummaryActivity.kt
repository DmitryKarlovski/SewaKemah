package com.app.sewakemah.view.main.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.data.CartEntity
import com.app.sewakemah.data.ProductEntity
import kotlinx.android.synthetic.main.activity_payment_summary.*
import kotlinx.android.synthetic.main.toolbar_detail_page.*

class PaymentSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_summary)

        initComponents()
    }

    private fun initComponents(){
        textView90.text = "Payment"

        summaryPurchase()

        imageView38.setOnClickListener {
            onBackPressed()
        }

        payment_layout_cc_clicked.setOnClickListener {
            payment_layout_cc.visibility = View.VISIBLE
            payment_layout_cc_clicked.visibility = View.GONE
        }

        payment_layout_va_clicked.setOnClickListener {

        }

        button32.setOnClickListener {
            startActivity(Intent(this, PaymentWaitingActivity::class.java))
        }
    }

    private fun summaryPurchase(){
        val cartSum = intent.extras!!.get("cart") as CartEntity

        textView128.text = "Rp 7.200.000"
        textView130.text = "Rp 0"
        textView134.text = "Rp 400.000"
        textView136.text = "Rp 7.600.000"
    }

}
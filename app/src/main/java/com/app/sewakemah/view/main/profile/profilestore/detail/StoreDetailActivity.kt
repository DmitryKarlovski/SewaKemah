package com.app.sewakemah.view.main.profile.profilestore.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.view.main.profile.profilestore.edit.StoreEditActivity
import kotlinx.android.synthetic.main.activity_store_detail.*

class StoreDetailActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

        button28.setOnClickListener{
            startActivity(Intent(this, StoreEditActivity::class.java))
        }
    }
}
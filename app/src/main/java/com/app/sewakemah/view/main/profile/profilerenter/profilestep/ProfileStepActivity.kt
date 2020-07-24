package com.app.sewakemah.view.main.profile.profilerenter.profilestep

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import kotlinx.android.synthetic.main.toolbar_detail_page.*

class ProfileStepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_step)

        initComponents()
    }

    private fun initComponents(){
        textView90.text = "Remaining Steps"
        imageView38.setOnClickListener {
            onBackPressed()
        }
    }
}
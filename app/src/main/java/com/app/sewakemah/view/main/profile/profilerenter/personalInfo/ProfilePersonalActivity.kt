package com.app.sewakemah.view.main.profile.personalInfo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import kotlinx.android.synthetic.main.activity_profile_detail.*
import kotlinx.android.synthetic.main.activity_profile_edit.*
import kotlinx.android.synthetic.main.toolbar_detail_page.*

class ProfilePersonalActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        var email = Preferences.getRegisteredEmail(baseContext)
        var userName = Preferences.getLoggedInUser(baseContext)
        var phoneNumber = Preferences.getKeyUserPhone(baseContext)
        var address = Preferences.getKeyUserAddress(baseContext)
        var photo = Preferences.getKeyUserPhoto(baseContext)

        textView60.text = email
        textView62.text = userName
        textView64.text = phoneNumber
        textView66.text = address

        if(photo.isNotEmpty()){
            imageView24.setImageBitmap(decodeBase64(photo))
        }

        initComponents()
    }

    private fun initComponents(){
        button20.setOnClickListener {
            startActivity(Intent(this, ProfileEditActivity::class.java))
        }

        imageView23.setOnClickListener {
            onBackPressed()
        }
    }

    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }
}
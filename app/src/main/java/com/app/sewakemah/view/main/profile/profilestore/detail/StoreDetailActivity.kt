package com.app.sewakemah.view.main.profile.profilestore.detail

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.view.main.profile.profilestore.edit.StoreEditActivity
import kotlinx.android.synthetic.main.activity_profile_detail.*
import kotlinx.android.synthetic.main.activity_store_detail.*
import kotlinx.android.synthetic.main.toolbar_detail_page.*

class StoreDetailActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

        var email = Preferences.getRegisteredEmail(baseContext)
        var userName = Preferences.getLoggedInUser(baseContext)
        var phoneNumber = Preferences.getKeyUserPhone(baseContext)
        var address = Preferences.getKeyUserAddress(baseContext)
        var photo = Preferences.getKeyUserStorePhoto(baseContext)
        var storeNama = Preferences.getKeyUserStore(baseContext)
        var storeAdd = Preferences.getKeyUserStoreAddress(baseContext)

        textView108.text = email
        textView110.text = userName
        textView112.text = phoneNumber
        textView114.text = address

        textView116.text = storeNama + storeAdd

        if(photo.isNotEmpty()){
            imageView42.setImageBitmap(decodeBase64(photo))
        }

        button28.setOnClickListener{
            startActivity(Intent(this, StoreEditActivity::class.java))
        }

        imageView38.setOnClickListener(){
            onBackPressed()
        }
    }

    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }
}
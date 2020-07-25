package com.app.sewakemah.view.main.profile.profilestore.edit

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.view.main.profile.personalInfo.ProfileEditActivity
import com.app.sewakemah.view.main.profile.personalInfo.ProfilePersonalActivity
import com.app.sewakemah.view.main.profile.profilestore.detail.StoreDetailActivity
import kotlinx.android.synthetic.main.activity_profile_edit.*
import kotlinx.android.synthetic.main.activity_store_edit.*
import java.io.ByteArrayOutputStream

class StoreEditActivity : AppCompatActivity(){

    private var storeeName: String? = ""
    private var storeePhone: String? = ""
    private var storeeAddress: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_edit)

        editTextTextPersonName13.setText(Preferences.getKeyUserStore(baseContext))
        editTextTextPersonName14.setText(Preferences.getKeyUserStorePhone(baseContext))
        editTextTextPersonName15.setText(Preferences.getKeyUserStoreAddress(baseContext))

        var photo = Preferences.getKeyUserStorePhoto(baseContext)

        if(photo.isNotEmpty()){
            imageView22.setImageBitmap(decodeBase64(photo))
        }

        initComponents()
    }

    private fun initComponents(){
        button17.setOnClickListener {
            //check runtime permission
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                    //permission denied
                    val permiss = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show pop up to request runtime permission
                    requestPermissions(permiss, permissionCode)
                }else {
                    //permission granted
                    launchGallery()
                }
            }else {
                //system OS <= Marshmallow
                launchGallery()
            }
        }

        imageView21.setOnClickListener {
            onBackPressed()
        }

        button18.setOnClickListener {
            //Save edited profile
            storeeName = editTextTextPersonName13.text.toString()
            storeePhone = editTextTextPersonName14.text.toString()
            storeeAddress = editTextTextPersonName15.text.toString()

            editCheck(storeeName!!, storeePhone!!, storeeAddress!!)

        }
    }

    private fun editCheck(name : String, num: String, address: String){
        editTextTextPersonName13.error = null
        editTextTextPersonName14.error = null
        editTextTextPersonName15.error = null

        var cancel1 = false
        var cancel2 = false
        var cancel3 = false


        //Store Name
        if(name!!.isEmpty()){
            editTextTextPersonName13.error = "Username is Empty"
            cancel1 = false
        }else if(name!!.length < 8){
            editTextTextPersonName13.error = "Username must be more than 8"
            cancel1 = false
        }else{
            editTextTextPersonName13.error = null
            cancel1 = true
        }

        //Store Phone Number
        if(num!!.isEmpty()) {
            editTextTextPersonName14.error = "Phone Number is Empty"
            cancel2 = false
//        }else if(num!!.toIntOrNull() == null){
//            editTextTextPersonName14.error = "Phone Number is not a number"
//            cancel2 = false
        }else if(num!!.take(2) != "08"){
            editTextTextPersonName14.error = "Phone Number must start with 08"
            cancel2 = false
        }else if(num!!.length < 10){
            editTextTextPersonName14.error = "Phone Number must be more than 10"
            cancel2 = false
        }else{
            editTextTextPersonName14.error = null
            cancel2 = true
        }

        //Address
        if(address!!.isEmpty()){
            editTextTextPersonName15.error = "Address is Empty"
            cancel3 = false
//        }else if(address!!.length < 8){
//            editTextTextPersonName15.error = "Address must be more than 8"
//            cancel3 = false
        }else{
            editTextTextPersonName15.error = null
            cancel3 = true
        }

        if(cancel1 && cancel2 && cancel3){
            saveStoreName(name!!)
            saveStorePhone(num!!)
            saveStoreAddress(address!!)

            val bitmap = (imageView22.getDrawable() as BitmapDrawable).bitmap
            encodeTobase64(bitmap)?.let { saveStorePhoto(it) }
            startActivity(Intent(this, StoreDetailActivity::class.java))
        }
    }

    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, imgCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            permissionCode -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchGallery()
                }else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == imgCode) {
            imageView22.setImageURI(data?.data)
        }
    }

    private fun encodeTobase64(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        val imageEncoded: String = Base64.encodeToString(b, Base64.DEFAULT)
        Log.d("Image Log:", imageEncoded)
        return imageEncoded
    }
    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    private fun saveStoreName(value: String) {
        Preferences.setKeyUserStore(this, value)
    }
    private fun saveStorePhone(value: String) {
        Preferences.setKeyUserStorePhone(this, value)
    }
    private fun saveStoreAddress(value: String) {
        Preferences.setKeyUserStoreAddress(this, value)
    }
    private fun saveStorePhoto(value: String) {
        Preferences.setKeyUserStorePhoto(this, value)
    }

    companion object {
        //image pick code
        private const val imgCode = 1000
        //permission code
        private const val permissionCode = 1001
    }
}
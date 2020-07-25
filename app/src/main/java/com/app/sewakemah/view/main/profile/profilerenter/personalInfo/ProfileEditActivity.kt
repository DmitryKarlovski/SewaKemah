package com.app.sewakemah.view.main.profile.personalInfo

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
import kotlinx.android.synthetic.main.activity_profile_edit.*
import java.io.ByteArrayOutputStream

class ProfileEditActivity : AppCompatActivity() {

    private var email: String? = ""
    private var userName: String? = ""
    private var phoneNumber: String? = ""
    private var address: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        editTextTextPersonName9.setText(Preferences.getRegisteredEmail(baseContext))
        editTextTextPersonName10.setText(Preferences.getLoggedInUser(baseContext))
        editTextTextPersonName11.setText(Preferences.getKeyUserPhone(baseContext))
        editTextTextPersonName12.setText(Preferences.getKeyUserAddress(baseContext))

        var photo = Preferences.getKeyUserPhoto(baseContext)

        if(photo.isNotEmpty()){
            imageView20.setImageBitmap(decodeBase64(photo))
        }

        initComponents()
    }

    private fun initComponents(){
        button15.setOnClickListener {
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

        imageView19.setOnClickListener {
            onBackPressed()
        }

        button16.setOnClickListener {
            //Save edited profile
            email = editTextTextPersonName9.text.toString()
            userName = editTextTextPersonName10.text.toString()
            phoneNumber = editTextTextPersonName11.text.toString()
            address = editTextTextPersonName12.text.toString()
            editCheck(email!!, userName!!, phoneNumber!!, address!!)

//            onBackPressed()
//            finish()
        }
    }

    private fun editCheck(mail: String, username : String, num: String, addres: String){
        editTextTextPersonName9.error = null
        editTextTextPersonName10.error = null
        editTextTextPersonName11.error = null
        editTextTextPersonName12.error = null
        var cancel1 = false
        var cancel2 = false
        var cancel3 = false
        var cancel4 = false

        //Email
        if(mail!!.isEmpty()){
            editTextTextPersonName9.error = "Email is Empty"
            cancel1 = false
        }else if(!mail!!.contains("@") || (!mail!!.contains(".com")&& !mail!!.contains(".co.id"))){
            editTextTextPersonName9.error = "Email must have @ and .com or .co.id"
            cancel1 = false
        }else{
            editTextTextPersonName9.error = null
            cancel1 = true
        }

        //User Name
        if(username!!.isEmpty()){
            editTextTextPersonName10.error = "Username is Empty"
            cancel2 = false
        }else if(username!!.length < 8){
            editTextTextPersonName10.error = "Username must be more than 8"
            cancel2 = false
        }else{
            editTextTextPersonName10.error = null
            cancel2 = true
        }

        //Phone Number
        if(num!!.isEmpty()) {
            editTextTextPersonName11.error = "Phone Number is Empty"
            cancel3 = false
//        }else if(num!!.toIntOrNull() == null){
//            editTextTextPersonName11.error = "Phone Number is not a number"
//            cancel3 = false
        }else if(num!!.take(2) != "08"){
            editTextTextPersonName11.error = "Phone Number must start with 08"
            cancel3 = false
        }else if(num!!.length < 10){
            editTextTextPersonName11.error = "Phone Number must be more than 10"
            cancel3 = false
        }else{
            editTextTextPersonName11.error = null
            cancel3 = true
        }

        //Address
        if(addres!!.isEmpty()){
            editTextTextPersonName12.error = "Address is Empty"
            cancel4 = false
//        }else if(addres!!.length < 8){
//            editTextTextPersonName12.error = "Address must be more than 8"
//            cancel4 = false
        }else{
            editTextTextPersonName12.error = null
            cancel4 = true
        }

        if(cancel1 && cancel2 && cancel3 && cancel4){
            saveEmail(mail!!)
            saveUser(username!!)
            savePhoneNumber(num!!)
            saveAddress(addres!!)

            val bitmap = (imageView20.getDrawable() as BitmapDrawable).bitmap
            encodeTobase64(bitmap)?.let { savePhoto(it) }
            startActivity(Intent(this, ProfilePersonalActivity::class.java))
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
            imageView20.setImageURI(data?.data)
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


    private fun saveEmail(value: String) {
        Preferences.setRegisteredEmail(this, value)
    }
    private fun saveUser(value: String) {
        Preferences.setRegisteredUser(this, value)
    }
    private fun savePhoneNumber(value: String) {
        Preferences.setKeyUserPhone(this, value)
    }
    private fun saveAddress(value: String) {
        Preferences.setKeyUserAddress(this, value)
    }
    private fun savePhoto(value: String) {
        Preferences.setKeyUserPhoto(this, value)
    }
    companion object {
        //image pick code
        private const val imgCode = 1000
        //permission code
        private const val permissionCode = 1001
    }
}
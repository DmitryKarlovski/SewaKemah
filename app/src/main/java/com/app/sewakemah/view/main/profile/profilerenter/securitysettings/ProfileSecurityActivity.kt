package com.app.sewakemah.view.main.profile.profilerenter.securitysettings

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.view.login.ForgotPasswordActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile_change_pass.*
import kotlinx.android.synthetic.main.toolbar_detail_page.*

class ProfileSecurityActivity : AppCompatActivity() {

    var oldPass: String? = ""
    var newPass: String? = ""
    var conPass: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change_pass)

        initComponents()
    }

    private fun initComponents(){
        textView90.text = "Security"
        imageView38.setOnClickListener {
            onBackPressed()
        }

        button27.setOnClickListener{
            oldPass = editTextTextPersonName18.text.toString()
            newPass = editTextTextPersonName21.text.toString()
            conPass = editTextTextPersonName22.text.toString()
            passwordCheck(oldPass!!, newPass!!, conPass!!)
        }
    }

    private fun passwordCheck(passLama :String, passBaru: String, passKon: String){
        editTextTextPersonName18.error = null
        editTextTextPersonName21.error = null
        editTextTextPersonName22.error = null
        //var username = Preferences.getRegisteredUser(baseContext)
        var cancel1 = false
        var cancel2 = false
        var cancel3 = false

        if(passLama.isEmpty()){
            editTextTextPersonName18.error = "Old Password is Empty"
            cancel1 = false
        }else if (!cekPassword(passLama)){
            editTextTextPersonName18.error = "This password is incorrect"
            cancel1 = false
        }else{
            editTextTextPersonName18.error = null
            cancel1 = true
        }

        if(passBaru.isEmpty()){
            editTextTextPersonName21.error = "New Password is Empty"
            cancel2 = false
        }else if (cekPassword(passBaru)){
            editTextTextPersonName21.error = "Password can't be same with old Password"
            cancel2 = false
        }else{
            editTextTextPersonName21.error = null
            cancel2 = true
        }

        if(passKon.isEmpty()){
            editTextTextPersonName22.error = "Confirm Password is Empty"
            cancel3 = false
        }else if (passKon != passBaru){
            editTextTextPersonName22.error = "Password doesn't match"
            cancel3 = false
        }else{
            editTextTextPersonName22.error = null
            cancel3 = true
        }

        if (cancel1 && cancel2 && cancel3){
            savePassword(passKon)
            Toast.makeText(this, "Change Password Successful", Toast.LENGTH_SHORT).show()
            //startActivity(Intent(this, MainActivity::class.java))
            onBackPressed()
        };

    }

    private fun cekPassword(password: String): Boolean {
        return password == Preferences.getRegisteredPass(baseContext)
    }
    private fun savePassword(value: String) {
        Preferences.setRegisteredPass(this, value)
    }

}
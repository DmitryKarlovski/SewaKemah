package com.app.sewakemah.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.view.WelcomeScreenActivity
import com.app.sewakemah.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    var email: String? = ""
    var password: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()
    }

    private fun loginCheck(mail : String, password: String){
        editTextTextPersonName.error = null
        editTextTextPersonName2.error = null
        var username = Preferences.getRegisteredUser(baseContext)
        var cancel1 = false
        var cancel2 = false

        if(mail.isEmpty()){
            editTextTextPersonName.error = "Username is Empty"
            cancel1 = false
        }else if (!cekMail(mail)){
            editTextTextPersonName.error = "This Username is not found"
            cancel1 = false
        }else{
            editTextTextPersonName.error = null
            cancel1 = true
        }

        if(password.isEmpty()){
            editTextTextPersonName2.error = "Password is Empty"
            cancel2 = false
        }else if (!cekPassword(password)){
            editTextTextPersonName2.error = "This password is incorrect"
            cancel2 = false
        }else{
            editTextTextPersonName.error = null
            cancel2 = true
        }

        if (cancel1 && cancel2){
            Toast.makeText(this, "Welcome $username",Toast.LENGTH_SHORT).show()
            login()
        };

    }

    private fun initComponents(){
        login_layout_forgotpass.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        button4.setOnClickListener {
            email = editTextTextPersonName.text.toString()
            password = editTextTextPersonName2.text.toString()
            loginCheck(email!!, password!!)
        }

        imageView3.setOnClickListener {
            startActivity(Intent(this, WelcomeScreenActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, WelcomeScreenActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(baseContext)) {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }

    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences  */
    private fun login() {
        Preferences.setLoggedInUser(
            baseContext,
            Preferences.getRegisteredUser(baseContext)
        )
        Preferences.setLoggedInStatus(baseContext, true)
        Preferences.setKeyEmailLoggedIn(baseContext, Preferences.getRegisteredEmail(baseContext))
        startActivity(Intent(baseContext, MainActivity::class.java))
        finish()
    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences  */
    private fun cekPassword(password: String): Boolean {
        return password == Preferences.getRegisteredPass(baseContext)
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences  */
    private fun cekMail(mail: String): Boolean {
        return mail == Preferences.getRegisteredEmail(baseContext)
    }
}
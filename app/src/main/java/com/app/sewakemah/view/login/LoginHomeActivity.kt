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


class LoginHomeActivity : AppCompatActivity() {
    var userName: String? = ""
    var password: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()
    }

    private fun loginCheck(username : String, password: String){
        editTextTextPersonName.error = null
        editTextTextPersonName2.error = null
        var cancel = false

        if(username.isEmpty()){
            editTextTextPersonName.error = "Username is Empty"
            cancel = false
        }else if (!cekUser(username)){
            editTextTextPersonName.error = "This Username is not found"
            cancel = false
        }else{
            cancel = true
        }

        if(password.isEmpty()){
            editTextTextPersonName2.error = "Password is Empty"
            cancel = false
        }else if (!cekPassword(password)){
            editTextTextPersonName2.error = "This password is incorrect"
            cancel = false
        }else{
            cancel = true
        }

        if (cancel){
            Toast.makeText(this, "Welcome $username",Toast.LENGTH_SHORT)
            login()
        };

    }

    private fun initComponents(){
        login_layout_forgotpass.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        button4.setOnClickListener {
            userName = editTextTextPersonName.text.toString()
            password = editTextTextPersonName2.text.toString()
            loginCheck(userName!!, password!!)
            startActivity(Intent(this, MainActivity::class.java))
        }

        imageView3.setOnClickListener {
            onBackPressed()
        }
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
        startActivity(Intent(baseContext, MainActivity::class.java))
        finish()
    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences  */
    private fun cekPassword(password: String): Boolean {
        return password == Preferences.getRegisteredPass(baseContext)
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences  */
    private fun cekUser(user: String): Boolean {
        return user == Preferences.getRegisteredUser(baseContext)
    }
}
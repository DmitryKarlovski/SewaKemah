package com.app.sewakemah.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.view.login.LoginActivity
import com.app.sewakemah.view.main.MainActivity
import com.app.sewakemah.view.register.RegisterMainActivity
import kotlinx.android.synthetic.main.activity_welcome_screen.*


class WelcomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        button.setOnClickListener {
            startActivity(Intent(this, RegisterMainActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(baseContext)) {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }
}
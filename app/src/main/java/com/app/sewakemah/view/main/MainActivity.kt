package com.app.sewakemah.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.sewakemah.R
import com.app.sewakemah.view.WelcomeScreenActivity
import com.app.sewakemah.view.main.cart.CartMainFragment
import com.app.sewakemah.view.main.home.HomeMainFragment
import com.app.sewakemah.view.main.liked.LikedMainFragment
import com.app.sewakemah.view.main.profile.ProfileMainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                val fragment = HomeMainFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_liked -> {
                val fragment = LikedMainFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_cart-> {
                val fragment = CartMainFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                val fragment = ProfileMainFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.home_frame_main, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = HomeMainFragment.newInstance()
        addFragment(fragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
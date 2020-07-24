package com.app.sewakemah.view.register

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.sewakemah.R


class RegisterMainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val firstFragment = RegisterOneFragment()
//    private val secondFragment = SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_main)

        /* Display First Fragment initially */
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_register, firstFragment)
        fragmentTransaction.commit()

    }

//    fun btnOne(v: View){
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.myFragment, firstFragment)
//        fragmentTransaction.commit()
//    }
//
//    fun btnTwo(v:View){
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.myFragment, secondFragment)
//        fragmentTransaction.commit()
//    }
}
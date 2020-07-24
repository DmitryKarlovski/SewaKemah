package com.app.sewakemah.view.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.sewakemah.R
import com.app.sewakemah.data.Preferences
import com.app.sewakemah.view.WelcomeScreenActivity
import kotlinx.android.synthetic.main.fragment_register_phase_one.*


class RegisterOneFragment : Fragment() {
    private var email: String? = ""//editTextTextPersonName?.text.toString()
    private var pass: String? = ""//editTextTextPersonName2?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_phase_one, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()

    }

    private fun registerCheck(){
        email = editTextTextPersonName.text.toString()
        pass = editTextTextPersonName2.text.toString()
        editTextTextPersonName.error = null
        editTextTextPersonName2.error = null
        var cancel1 = false
        var cancel2 = false

        if(email!!.isEmpty()){
            editTextTextPersonName.error = "Email is Empty"
            cancel1 = false
        }else if("@" !in email!! || (".com" !in email!! && ".co.id" !in email!!)){
            editTextTextPersonName.error = "Email must have @ and .com or .co.id"
            cancel1 = false
        }else{
            editTextTextPersonName.error = null
            cancel1 = true
        }

        if(pass!!.isEmpty()){
            editTextTextPersonName2.error = "Password is Empty"
            cancel2 = false
        }else if(pass!!.length <= 8){
            editTextTextPersonName2.error = "Password must be more than 8"
            cancel2 = false
        }else{
            editTextTextPersonName2.error = null
            cancel2 = true
        }

        if(cancel1 && cancel2){
            saveEmail(email!!)
            savePassword(pass!!)

            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(
                R.id.frame_register, RegisterTwoFragment()
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initComponents(){

        regs_btn_continue.setOnClickListener {
            registerCheck()
        }

        imageView3.setOnClickListener {
            startActivity(Intent(context, WelcomeScreenActivity::class.java))
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun saveEmail(value: String) {
        Preferences.setRegisteredEmail(context, value)
    }
    private fun savePassword(value: String) {
        Preferences.setRegisteredPass(context, value)
    }



    companion object {
        fun newInstance(): RegisterOneFragment {
            val fragment = RegisterOneFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
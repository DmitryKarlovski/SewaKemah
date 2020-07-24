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
import com.app.sewakemah.view.login.ForgotPasswordActivity
import com.app.sewakemah.view.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_register_phase_one.*
import kotlinx.android.synthetic.main.fragment_register_phase_two.*

class RegisterTwoFragment : Fragment() {
    private var user: String? = ""//editTextTextPersonName3?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_phase_two, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()

    }

    private fun initComponents(){
        button3.setOnClickListener{
            loginCheck()
        }

        imageView2.setOnClickListener {
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(
                R.id.frame_register, RegisterOneFragment()
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun loginCheck(){
        user = editTextTextPersonName3.text.toString()
        editTextTextPersonName3.error = null
        var cancel = false

        if(user!!.isEmpty()){
            editTextTextPersonName3.error = "Username is Empty"
            cancel = false
        }else if(user!!.length <= 8){
            editTextTextPersonName3.error = "Username must be more than 8"
            cancel = false
        }else{
            editTextTextPersonName3.error = null
            cancel = true
        }

        if(cancel){
            saveUser(user!!)
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private fun saveUser(value: String) {
        Preferences.setRegisteredUser(context, value)
    }

    companion object {
        fun newInstance(): RegisterTwoFragment {
            val fragment = RegisterTwoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
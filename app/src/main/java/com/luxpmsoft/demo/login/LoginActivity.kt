package com.luxpmsoft.demo.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.luxpmsoft.demo.R
import com.luxpmsoft.demo.dashboard.DashboardActivity
import com.luxpmsoft.demo.databinding.ActivityLoginBinding
import com.luxpmsoft.demo.utils.UtilityKotlin

class LoginActivity : AppCompatActivity(), AuthListener {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // we use data bindind insted of setcontent view with will auto create a new name of Loginactivty class
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        // Now we have to call viewmodel here with viewmodel class name in get method
        var viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        // Now we set this view model to our binding viewmodel
        binding.viewmodel = viewModel
        // set authlister to our view model
        viewModel.authListener = this
    }

    override fun onStarted() {
        UtilityKotlin.logData("Login Started")
//        binding.editPassword.text = null
    }

    override fun onSuccess(loginResponseFromUserRepository: LiveData<String>) {
        loginResponseFromUserRepository.observe(this, Observer
        {
            UtilityKotlin.callNextActivity(this, DashboardActivity::class.java)
        })
    }

    override fun onFailure(message: String) {
        /*if (message == "Wrong combination") {
            UtilityKotlin.hideKeypad(this)
            binding.editPassword.error = message
        } else {
//            binding.editPassword.text = null
            UtilityKotlin.showPopUp(this, message)
        }*/

        UtilityKotlin.showPopUp(this, message)
    }


}

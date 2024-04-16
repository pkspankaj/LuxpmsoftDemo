package com.luxpmsoft.demo.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luxpmsoft.demo.utils.UtilityKotlin

class AuthViewModel : ViewModel() {

    // define variable initial value is null that why ? use as null safety operator
    var email: String? = null
    var password: String? = null

    // call auth listener interface for use as callback to activity
    var authListener: AuthListener? = null


    // function for if user click on login button
    fun onLoginButtonClick(view: View) {
        // when button click first we will call interface method on started here
        authListener?.onStarted()

        // check data validation
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Please enter user name")
            return
        } else if (!UtilityKotlin.isEmailValid(email.toString().trim())) {
            authListener?.onFailure("Please enter valid user name")
            return
        } else if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter password")
            return
        } else if (!UtilityKotlin.isValidPassword(password.toString().trim())) {
            authListener?.onFailure("Wrong combination")
            return
        } else if (password.toString().trim() != "test1234!") {
            authListener?.onFailure("the provided password is wrong")
            return
        } else if (!UtilityKotlin.isValidEmailPassword(
                email.toString().trim(),
                password.toString().trim()
            )
        ) {
            authListener?.onFailure("Invalid Login Detail")
            return
        } else {
            val loginResponse = MutableLiveData<String>()
            loginResponse.value = "Done";
            authListener?.onSuccess(loginResponse)

        }

    }

}
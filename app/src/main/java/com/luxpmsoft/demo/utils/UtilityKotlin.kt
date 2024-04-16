package com.luxpmsoft.demo.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import com.luxpmsoft.demo.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class UtilityKotlin {
    companion object {

        fun logData(logData: String) {

            Log.v("logData", logData)
        }

        fun callNextActivity(activity: Activity, aClass: Class<*>?) {
            val i = Intent(activity, aClass)
            activity.startActivity(i)
        }

        fun isEmailValid(email: CharSequence?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPassword(password: String): Boolean {
            val pattern: Pattern
            val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
            val PASSWORD_REGEX =
                "^(?=.*[a-z])(?=.*[0-9])(?=.*[$specialCharacters])(?=\\S+$).{9,20}$"
            pattern = Pattern.compile(PASSWORD_REGEX)
            val matcher: Matcher = pattern.matcher(password)
            return matcher.matches()
        }

        fun isValidEmailPassword(email: String, password: String): Boolean {
            return if (email == "test@luxpmsoft.com" && password == "test1234!") {
                true
            } else {
                false
            }
        }

        fun showPopUp(context: Context, str: String?) {
            Handler(Looper.getMainLooper()).post {
                try {
                    val activity: Activity = context as Activity
                    if (!activity.isFinishing) {
                        val alertDialog2 = AlertDialog.Builder(context)

                        // Setting Dialog Title
                        alertDialog2.setTitle("")
                        alertDialog2.setCancelable(false)

                        // Setting Dialog Message
                        alertDialog2.setMessage(str)

                        // Setting Positive "Yes" Btn
                        alertDialog2.setPositiveButton(
                            context.getString(R.string.ok)
                        ) { dialog, which -> dialog.dismiss() }

                        // Showing Alert Dialog
                        alertDialog2.show()

                    }
                } catch (e: Exception) {
                    logData(e.message.toString())
                }
            }
        }

        fun hideKeypad(activity: Activity) {
            try {
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val currentFocusedView = activity.currentFocus
                if (currentFocusedView != null) {
                    inputManager.hideSoftInputFromWindow(
                        currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            } catch (e: Exception) {
                logData(e.message.toString())
            }

        }
    }
}

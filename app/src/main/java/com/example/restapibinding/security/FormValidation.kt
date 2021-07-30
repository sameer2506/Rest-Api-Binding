package com.example.restapibinding.security

import android.widget.EditText

fun mobileNumberValidation(mobileNumber: String?) : Boolean {
    return when {
        mobileNumber.isNullOrEmpty() ->
            false
        phoneNumberPattern(mobileNumber) -> true
        else ->
            false
    }
}

fun validateET(et: EditText):Boolean {
    return if(et.text.isNullOrBlank()){
        et.error = "Please enter ${et.hint} here"
        false
    }else{
        true
    }
}
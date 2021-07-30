package com.example.restapibinding.util

import android.app.DatePickerDialog
import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.restapibinding.data.network.Resource
import com.google.android.material.snackbar.Snackbar
import java.util.*

fun log(message: String){
    Log.d("restApi_test",message)
}

fun Context.toast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun handleDateButton(context: Context, Etv: EditText){
    val calendar = Calendar.getInstance()
    val YEAR = calendar[Calendar.YEAR]
    val MONTH = calendar[Calendar.MONTH]
    val DATE = calendar[Calendar.DATE]
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, date ->
            val calendar1 = Calendar.getInstance()
            calendar1[Calendar.YEAR] = year
            calendar1[Calendar.MONTH] = month
            calendar1[Calendar.DATE] = date

            val dateText: String = DateFormat.format("dd MMM yyyy", calendar1).toString()
            val dateText2: String = DateFormat.format("yyyy-mm-dd", calendar1).toString()

            Etv.setText(dateText)
        }, YEAR, MONTH, DATE
    )
    datePickerDialog.show()
}


fun handleApiError(
    failure: Resource.Failure,
    context: Context,
    view: View
) {
    when {
        failure.isNetworkError -> {
            log("failure.isNetworkError : Make sure you are connected to internet.")
            view.snackbar(
                "Make sure you are connected to internet."
            )
        }
        failure.errorCode == 400 -> {
            log("Bad request : 400")
            context.toast("Bad request : 400")
        }
        failure.errorCode == 401 -> {
            log("Unauthorized : 401")
            context.toast("Unauthorized : 401")
        }
        failure.errorCode == 403 -> {
            log("Forbidden : 403")
            context.toast("Forbidden : 403")
        }
        failure.errorCode == 404 -> {
            log("Not found : 404")
            context.toast("Not found : 404")
        }
        failure.errorCode == 405 -> {
            log("Method not followed : 405")
            context.toast("Method not followed : 405")
        }
        failure.errorCode == 406 -> {
            log("Not acceptable : 406")
            context.toast("Not acceptable : 406")
        }
        failure.errorCode == 407 -> {
            log("Proxy authentication required : 407")
            context.toast("Proxy authentication required : 407")
        }
        failure.errorCode == 408 -> {
            log("Request timeout : 408")
            context.toast("Request timeout : 408")
        }
        else -> {
            log("error : ${failure.errorLog}")
            if(failure.errorToast =="Make sure you are connected to internet."){
                view.snackbar(failure.errorToast)
            }else{
                context.toast(failure.errorToast!!)
            }
        }
    }
}


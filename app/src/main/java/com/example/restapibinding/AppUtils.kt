package com.example.restapibinding

import android.app.DatePickerDialog
import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import android.widget.EditText
import java.util.*

fun log(message: String){
    Log.d("cms_app_test",message)
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

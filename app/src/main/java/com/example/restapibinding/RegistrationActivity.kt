package com.example.restapibinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val items = listOf("Male", "Female")
        val adapter = ArrayAdapter(this, R.layout.item_gender, items)
        (etGender.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        etDOB.setOnClickListener {
            handleDateButton(this, etDOB)
        }
    }

    private fun registration(){
        if (etDOB.text.isEmpty()) {
            etDOB.error = "Date is not choose"
            log("Date is not choose")
            return
        }
    }
}
package com.example.restapibinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restapibinding.ui.registration.RegistrationActivity
import com.example.restapibinding.ui.weather.WeatherActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, WeatherActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
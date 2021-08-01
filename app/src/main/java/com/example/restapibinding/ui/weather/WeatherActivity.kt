package com.example.restapibinding.ui.weather

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.restapibinding.data.network.Resource
import com.example.restapibinding.data.network.responses.weather.WeatherDetails
import com.example.restapibinding.databinding.ActivityWeatherBinding
import com.example.restapibinding.security.appid
import com.example.restapibinding.security.validateET
import com.example.restapibinding.ui.registration.RegistrationActivity
import com.example.restapibinding.util.handleApiError
import com.example.restapibinding.util.log
import com.example.restapibinding.util.show
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.math.RoundingMode
import java.text.DecimalFormat

class WeatherActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory : WeatherVMF by instance()

    private lateinit var binding : ActivityWeatherBinding
    private lateinit var viewModel: WeatherVM

    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        view = binding.root
        viewModel = ViewModelProvider(this, factory).get(WeatherVM::class.java)
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        binding.btnShowResult.setOnClickListener {
            showResult()
        }



    }

    private fun showResult(){

        log("showResult()")

        if (!validateET(binding.etCityName.editText!!))
            return

        val city = binding.etCityName.editText!!.text.toString()

        log("city: ")
        log(city)

        viewModel.getWeatherDetails(city, appid)

        viewModel.weatherDetails.observe(this, {
            when (it) {
                is Resource.Success -> {
                    onSuccess(it.value)
                }
                is Resource.Loading -> {
                }
                is Resource.Failure -> {
                    handleApiError(it, this, view)
                }
            }
        })
    }

    private fun onSuccess(response: WeatherDetails){
        log("weather details:-")
        log("$response")

        val longitude = response.coord.lon
        val latitude = response.coord.lat
        val temp = response.main.temp

        val tempInCel = (temp - 273.15)
        val tempInFar = tempInCel * 1.8 + 32
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING


        with(binding){
            tvTempCent.text = df.format(tempInCel)
            tvTempFah.text = df.format(tempInFar)
            tvLatitude.text = latitude.toString()
            tvLongitude.text = longitude.toString()
            tvSymbolCel.show()
            tvSymabolFar.show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, RegistrationActivity::class.java))
        finish()
    }

}
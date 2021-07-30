package com.example.restapibinding.ui.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.restapibinding.R
import com.example.restapibinding.databinding.ActivityRegistrationBinding
import com.example.restapibinding.databinding.ActivityWeatherBinding
import com.example.restapibinding.ui.registration.RegistrationVM
import com.example.restapibinding.ui.registration.RegistrationVMF
import com.example.restapibinding.util.log
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

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

        lifecycleScope.launch {
            val user = viewModel.getUserRoomData()
            log("user:-")
            log(user.toString())
        }

    }
}
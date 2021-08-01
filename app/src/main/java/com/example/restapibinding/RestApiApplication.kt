package com.example.restapibinding

import android.app.Application
import com.example.restapibinding.data.db.AppDatabase
import com.example.restapibinding.data.network.PincodeApi
import com.example.restapibinding.data.network.NetworkConnectionInterceptor
import com.example.restapibinding.data.network.WeatherApi
import com.example.restapibinding.data.repositories.UserRepository
import com.example.restapibinding.data.repositories.WeatherRepository
import com.example.restapibinding.ui.registration.RegistrationVMF
import com.example.restapibinding.ui.weather.WeatherVMF
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class RestApiApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@RestApiApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { PincodeApi(instance()) }
        bind() from singleton { WeatherApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { WeatherRepository(instance(), instance()) }
        bind() from provider { RegistrationVMF(instance()) }
        bind() from provider { WeatherVMF(instance()) }
    }

}
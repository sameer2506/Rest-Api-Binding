package com.example.restapibinding.data.network

import com.example.restapibinding.data.network.responses.weather.WeatherDetails
import com.example.restapibinding.security.WEATHER_API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeatherDetails(
        @Query("q") q : String,
        @Query("appid") appid : String
    ) : WeatherDetails

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : WeatherApi {

            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(networkConnectionInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(WEATHER_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
        }
    }

}
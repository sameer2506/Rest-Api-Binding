package com.example.restapibinding.data.network

import com.example.restapibinding.data.network.responses.pincode.PincodeDetails
import com.example.restapibinding.security.PINCODE_API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface PincodeApi {

    @GET("pincode/{pincode}")
    suspend fun getPincodeDetails(
        @Path("pincode") pincode: String
    ) : PincodeDetails

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : PincodeApi {

            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(networkConnectionInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(PINCODE_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PincodeApi::class.java)
        }
    }

}
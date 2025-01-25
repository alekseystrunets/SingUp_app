package com.example.singup_app.data.api.retrofitClient

import com.example.singup_app.data.api.server.ServerInt
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://collectionapi.metmuseum.org"

    val instance: ServerInt by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerInt::class.java)
    }
}
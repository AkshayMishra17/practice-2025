package com.example.project.nw

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiObject {
//    val baseUrl = "https://api.thecatapi.com/"

    const val baseUrl = "https://fakestoreapi.com/"
    val retroInst : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}
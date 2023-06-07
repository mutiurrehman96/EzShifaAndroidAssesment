package com.zamulk.ezshifaassesmentmuti.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL: String = " https://jsonplaceholder.typicode.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var service: EndPoints = retrofit.create(EndPoints::class.java)

    fun endPointsService(): EndPoints {
        return service
    }
}
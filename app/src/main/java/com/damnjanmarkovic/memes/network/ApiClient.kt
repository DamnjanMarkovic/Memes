package com.damnjanmarkovic.memes.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    //base URL
    private val BASE_URL = "https://api.imgflip.com/"

    //create variable for moshi builder, adding a converter to it
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    //create a instance of Retrofit by lazy so it's created only when needed

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
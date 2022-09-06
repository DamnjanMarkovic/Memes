package com.damnjanmarkovic.memes.network
import com.damnjanmarkovic.memes.models.MemeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    //fetch memes method
    @GET("get_memes")
    fun fetchMemes(): Call<MemeResponse>
}
package com.damnjanmarkovic.memes.models

import com.squareup.moshi.Json

data class MemeResponse(
    @Json(name="data")
    val `data`: Data?,
    @Json(name="success")
    val success: Boolean?)

data class Data(
    @Json(name="memes")
    val memes: List<Meme?>?
)

data class Meme(
    @Json(name="boxCount")
    val boxCount: Int?,
    @Json(name="height")
    val height: Int?,
    @Json(name="id")
    val id: String?,
    @Json(name="name")
    val name: String?,
    @Json(name="url")
    val url: String?,
    @Json(name="width")
    val width: Int?
)
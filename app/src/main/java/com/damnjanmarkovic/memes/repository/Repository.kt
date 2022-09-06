package com.damnjanmarkovic.memes.repository

import com.damnjanmarkovic.memes.network.ApiService

class Repository(private val apiService: ApiService) {
    fun getCharacters(page: String) = apiService.fetchMemes()
}
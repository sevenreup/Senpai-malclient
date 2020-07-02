package com.skybox.seven.senpai.api.jikan.api

import retrofit2.http.GET

interface JikanService {
    @GET("/search/{type}")
    fun search(type: String)
}
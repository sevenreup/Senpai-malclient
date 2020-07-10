package com.skybox.seven.senpai.api.jikan.api

import com.skybox.seven.senpai.api.jikan.model.CharacterStaff
import com.skybox.seven.senpai.api.jikan.model.Season
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanService {
    @GET("search/{type}")
    fun search(@Path(value = "type") type: String)

    @GET("season/{year}/{season}")
    fun getSeason(@Path(value = "year") year: Int, @Path(value = "season") season: String): Call<Season>

    @GET("anime/{id}/characters_staff")
    fun getCharacterStaff(@Path(value = "id") id: Int): Call<CharacterStaff>
}
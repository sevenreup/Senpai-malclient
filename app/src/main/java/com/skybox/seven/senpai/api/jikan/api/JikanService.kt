package com.skybox.seven.senpai.api.jikan.api

import com.skybox.seven.senpai.api.jikan.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanService {
    @GET("search/{type}")
    fun search(@Path(value = "type") type: String)

    @GET("season/{year}/{season}")
    fun getSeason(@Path(value = "year") year: Int, @Path(value = "season") season: String): Call<Season>

    @GET("anime/{id}")
    fun getAnimeFull(@Path(value = "id") id: Int): Call<AnimeFull>

    @GET("anime/{id}/characters_staff")
    fun getCharacterStaff(@Path(value = "id") id: Int): Call<CharacterStaff>

    @GET("anime/{id}/pictures")
    fun getPictures(@Path(value = "id") id: Int): Call<PicturesResponse>

    @GET("anime/{id}/videos")
    fun getVideos(@Path(value = "id") id: Int): Call<VideosResponse>

    @GET("anime/{id}/stats")
    fun getStats(@Path(value = "id") id: Int): Call<StatsResponse>

}
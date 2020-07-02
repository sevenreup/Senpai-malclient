package com.skybox.seven.senpai.api.mal.api

import com.skybox.seven.senpai.api.mal.model.AuthResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MALService {
    @FormUrlEncoded
    @POST("auth/token")
    fun login(@Field("client_id") clientID: String, @Field("grant_type  ") grantType: String,
              @Field("username")  username: String, @Field("password") password: String): Call<AuthResponse>

    @FormUrlEncoded
    @POST("auth/token")
    fun refreshToken(@Field("client_id") clientID: String, @Field("grant_type  ") grantType: String,
                      @Field("refresh_token")  refreshToken: String): Call<AuthResponse>

}
package com.skybox.seven.core.mal.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MALService {
    @FormUrlEncoded
    @POST("/auth/token")
    suspend fun login(@Field("client_id") clientID: String, @Field("grant_type  ") grantType: String,
                      @Field("username")  username: String, @Field("password") password: String)

    @FormUrlEncoded
    @POST("/auth/token")
    suspend fun refreshToken(@Field("client_id") clientID: String, @Field("grant_type  ") grantType: String,
                      @Field("refresh_token")  refreshToken: String)
}
package com.skybox.seven.core.di

import com.google.gson.GsonBuilder
import com.skybox.seven.core.mal.api.MALService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MalModule {
    fun providesMalService(client: Lazy<OkHttpClient>): MALService =
        Retrofit.Builder()
            .baseUrl(MALService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(MALService::class.java)
}
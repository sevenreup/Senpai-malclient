package com.skybox.seven.senpai.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.skybox.seven.senpai.SenpaiApplication
import com.skybox.seven.senpai.api.mal.api.ClientInterceptor
import com.skybox.seven.senpai.api.mal.api.MALService
import com.skybox.seven.senpai.data.source.AuthTokenDataSource
import com.skybox.seven.senpai.data.source.PreferencesRepository
import com.skybox.seven.senpai.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(clientInterceptor: ClientInterceptor): OkHttpClient  {
        val loggingInterceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(clientInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesClientInterceptor(authTokenDataSource: AuthTokenDataSource):ClientInterceptor =
        ClientInterceptor(authTokenDataSource, Constants.CLIENT_ID)

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SenpaiApplication.SHARED_PREFS, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesAuthTokenDataSource(sharedPreferences: SharedPreferences):AuthTokenDataSource =
        AuthTokenDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun providesMalService(retrofit: Retrofit): MALService =
        retrofit.create(MALService::class.java)

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    @Singleton
    @Provides
    fun providesPreferencesRepository(sharedPreferences: SharedPreferences): PreferencesRepository =
        PreferencesRepository(sharedPreferences)

}
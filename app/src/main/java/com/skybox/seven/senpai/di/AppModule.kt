package com.skybox.seven.senpai.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.skybox.seven.senpai.SenpaiApplication
import com.skybox.seven.senpai.api.jikan.api.JikanService
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
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Qualifier annotation class MalApiBuilder
    @Qualifier annotation class MalApiClient

    @Qualifier annotation class JikanApiBuilder
    @Qualifier annotation class JikanApiClient

    // Mal Config
    @MalApiClient
    @Singleton
    @Provides
    fun providesMalOkHttpClient(authTokenDataSource: AuthTokenDataSource): OkHttpClient  {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ClientInterceptor(authTokenDataSource, Constants.CLIENT_ID))
            .build()
    }

    @Singleton
    @Provides
    fun providesMalService(@MalApiBuilder retrofit: Retrofit): MALService =
        retrofit.create(MALService::class.java)

    @MalApiBuilder
    @Singleton
    @Provides
    fun providesMalRetrofit(@MalApiClient client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.MAL_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    // Jikan Config
    @JikanApiClient
    @Singleton
    @Provides
    fun providesJikanOkHttpClient(): OkHttpClient  {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesJikanService(@JikanApiBuilder retrofit: Retrofit): JikanService =
        retrofit.create(JikanService::class.java)

    @JikanApiBuilder
    @Singleton
    @Provides
    fun providesJikanRetrofit(@JikanApiClient client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.JIKAN_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()


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
    fun providesPreferencesRepository(sharedPreferences: SharedPreferences): PreferencesRepository =
        PreferencesRepository(sharedPreferences)

}
package com.skybox.seven.core.mal.api

import com.skybox.seven.core.mal.login.AuthTokenDataSource
import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor(private val tokenDataSource: AuthTokenDataSource, private val client_id: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        if (!tokenDataSource.authToken.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization",
                "Bearer ${tokenDataSource.authToken}")
        }

        requestBuilder.addHeader("X-MAL-Client-ID", client_id);

        return chain.proceed(requestBuilder.build())
    }

}
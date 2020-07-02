package com.skybox.seven.senpai.data.source

import android.content.SharedPreferences
import androidx.core.content.edit

class AuthTokenDataSource(private val prefs: SharedPreferences) {
    private var _authToken: String? = prefs.getString(ACCESS_TOKEN, null)
    private var _refreshToken: String? = prefs.getString(REFRESH_TOKEN, null)

    var authToken: String? = _authToken
        set(value) {
            prefs.edit { putString(ACCESS_TOKEN, value)}
            field = value
        }

    var refreshToken: String? = _refreshToken
    set(value) {
        prefs.edit { putString(REFRESH_TOKEN, value)}
        field = value
    }

    fun clear() {
        prefs.edit { ACCESS_TOKEN to null}
        prefs.edit { REFRESH_TOKEN to null}
        refreshToken = null
        authToken = null
    }

    companion object {
        private const val ACCESS_TOKEN = "MAL_ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "MAL_REFRESH_TOKEN"

        @Volatile private var INSTANCE: AuthTokenDataSource? = null

        fun getInstance(sharedPreferences: SharedPreferences): AuthTokenDataSource {
            return INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: AuthTokenDataSource(
                        sharedPreferences
                    ).also {
                    INSTANCE = it
                }
            }
        }
    }
}
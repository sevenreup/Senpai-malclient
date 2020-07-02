package com.skybox.seven.core.mal.model

import com.google.gson.annotations.SerializedName

class AuthResponse {
    @SerializedName("token_type")
    var tokenType: String? = null

    @SerializedName("access_token")
    var accessToken: String? = null

    @SerializedName("refresh_token")
    var refreshToken: String? = null

    @SerializedName("expires_in")
    var expiresIn: String? = null

}
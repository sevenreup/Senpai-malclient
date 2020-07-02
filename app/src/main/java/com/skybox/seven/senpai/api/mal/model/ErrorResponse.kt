package com.skybox.seven.senpai.api.mal.model

import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName("error")
    var error: String? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("hint")
    var hint: String? = null
}
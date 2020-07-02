package com.skybox.seven.senpai.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.senpai.api.mal.api.MALService
import com.skybox.seven.senpai.api.mal.model.AuthResponse
import com.skybox.seven.senpai.data.source.AuthTokenDataSource
import com.skybox.seven.senpai.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel @ViewModelInject constructor(private val malService: MALService, private val authTokenDataSource: AuthTokenDataSource): ViewModel() {
    private val TAG = "LoginViewModel"
    val loginDetails = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        Log.e(TAG, "$username $password")
        malService.login(Constants.CLIENT_ID, Constants.GRANT_TYPE,username, password)
            .enqueue(object: Callback<AuthResponse> {
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    t.printStackTrace()
                    loginDetails.value = false
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful) {
                        authTokenDataSource.authToken = response.body()?.accessToken
                        authTokenDataSource.refreshToken = response.body()?.refreshToken
                        loginDetails.value = true
                    } else {

                        Log.e(TAG, response.toString())
                        when (response.code()) {
                            400 -> {
                                Log.e(TAG, "Password or username is wrong")
                            }
                        }
                        loginDetails.value = false
                    }

                }
            })
    }
}
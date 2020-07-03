package com.skybox.seven.senpai.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.senpai.api.jikan.api.JikanService
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.api.jikan.model.Season
import com.skybox.seven.senpai.util.RandomGen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val TAG = "HomeViewModel"

class HomeViewModel @ViewModelInject constructor(private val jikanService: JikanService) : ViewModel() {
    var randomSpotAnime: MutableLiveData<Anime> = MutableLiveData()

    fun getRandomSeason() {
        val calender = Calendar.getInstance().get(Calendar.YEAR) - 20
        val year = RandomGen.getRandomInt(calender - 10, calender)
        val  season = RandomGen.randomSeason()

        jikanService.getSeason(year, season).enqueue(object : Callback<Season> {
            override fun onFailure(call: Call<Season>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Season>, response: Response<Season>) {
                if (response.isSuccessful) {
                    val season = response.body()
                    val anime = season?.anime?.get(RandomGen.getRandomInt(0, season?.anime.size - 1))
                    randomSpotAnime.value = anime
                } else {
                    Log.e(TAG, "onResponse: failure ${response.message()}")
                }
            }

        })
    }
}
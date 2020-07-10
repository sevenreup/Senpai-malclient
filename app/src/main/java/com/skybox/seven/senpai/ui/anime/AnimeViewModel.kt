package com.skybox.seven.senpai.ui.anime

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.senpai.api.jikan.api.JikanService
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.api.jikan.model.StatsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "AnimeViewModel"
class AnimeViewModel @ViewModelInject constructor(private val jikanService: JikanService): ViewModel() {
     val activeAnimeData: MutableLiveData<Anime> = MutableLiveData()
     val animeStatsData: MutableLiveData<StatsResponse> = MutableLiveData()

     fun getStats(id: Int?) {
          jikanService.getStats(id!!).enqueue(object: Callback<StatsResponse> {
               override fun onFailure(call: Call<StatsResponse>, t: Throwable) {
                    t.printStackTrace()
               }

               override fun onResponse(
                    call: Call<StatsResponse>,
                    response: Response<StatsResponse>
               ) {
                    Log.e(TAG, "onResponse: $response ${response.body()}")
               }

          })
     }
}
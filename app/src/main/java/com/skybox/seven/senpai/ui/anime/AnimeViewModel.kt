package com.skybox.seven.senpai.ui.anime

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.senpai.api.jikan.api.JikanService
import com.skybox.seven.senpai.api.jikan.model.Anime

class AnimeViewModel @ViewModelInject constructor(private val jikanService: JikanService): ViewModel() {
     val activeAnimeData: MutableLiveData<Anime> = MutableLiveData()


}
package com.skybox.seven.senpai.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeSharedViewModel(): ViewModel() {
    val scrollDirection: MutableLiveData<Int> = MutableLiveData()
}
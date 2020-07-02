package com.skybox.seven.senpai

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SenpaiApplication : Application() {
companion object {
    var SHARED_PREFS = "SENPAI_PREFS"
}
}
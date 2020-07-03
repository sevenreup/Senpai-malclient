package com.skybox.seven.senpai.util

import kotlin.math.ceil
import kotlin.math.floor

object RandomGen {
    fun getRandomInt(min: Int, max: Int): Int {
        var min = min.toDouble()
        var max = max.toDouble()
        min = ceil(min)
        max = floor(max)
        return (floor(Math.random() * (max - min + 1)) + min).toInt()
    }

    fun randomSeason(): String {
        return Constants.SEASONS[getRandomInt(0, Constants.SEASONS.size - 1)]
    }
}
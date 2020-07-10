package com.skybox.seven.senpai.util

import android.content.Context
import android.content.res.Configuration
import androidx.palette.graphics.Palette

fun getDarkSwatch(palette: Palette):Palette.Swatch {
    return palette.darkVibrantSwatch ?: (palette.darkMutedSwatch ?: (palette.mutedSwatch ?: getLightSwatch(palette)))
}

fun getLightSwatch(palette: Palette): Palette.Swatch {
    return palette.vibrantSwatch ?: (palette.lightVibrantSwatch ?: (palette.lightMutedSwatch ?: getDarkSwatch(palette)))
}

fun getMode(context: Context): Int {
    return context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
}
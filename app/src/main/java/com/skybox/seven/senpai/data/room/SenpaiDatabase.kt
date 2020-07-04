package com.skybox.seven.senpai.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skybox.seven.senpai.data.room.dayanime.AnimeOfTheDay
import com.skybox.seven.senpai.data.room.dayanime.AnimeOfTheDayDAO

@Database(entities = [AnimeOfTheDay::class], version = 1, exportSchema = false)
abstract class SenpaiDatabase: RoomDatabase() {
    abstract fun animeOfTheDayDao(): AnimeOfTheDayDAO
}
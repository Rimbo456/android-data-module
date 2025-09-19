package com.example.android_data_module.data.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_data_module.data.movie.datasource.local.MovieDao
import com.example.android_data_module.data.movie.datasource.local.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
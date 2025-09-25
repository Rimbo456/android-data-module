package com.example.android_data_module.data.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android_data_module.data.movie.datasource.local.MovieDao
import com.example.android_data_module.data.movie.datasource.local.MovieEntity

@Database(entities = [MovieEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE movie ADD COLUMN releaseDate TEXT")
                db.execSQL("ALTER TABLE movie ADD COLUMN voteAverage REAL")
            }
        }
    }
}
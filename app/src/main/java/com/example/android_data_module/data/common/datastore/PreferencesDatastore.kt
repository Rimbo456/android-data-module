package com.example.android_data_module.data.common.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesDatastore @Inject constructor(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "app_preferences")
    private val datastore = context.dataStore

    val languageFlow: Flow<String?> = datastore.data.map { preferences ->
        preferences[PreferencesKeys.APP_LANGUAGE]
    }

    suspend fun updateLanguage(language: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKeys.APP_LANGUAGE] = language
        }
    }

    fun getDeviceLanguage(): String {
        return Locale.getDefault().language
    }
}
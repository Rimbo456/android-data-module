package com.example.android_data_module.data.common.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.android_data_module.domain.repository.UserSettingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSettingRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): UserSettingRepository {
    private val Context.dataStore by preferencesDataStore(name = "app_preferences")
    private val datastore = context.dataStore

    override val languageFlow: Flow<String?> = datastore.data.map { preferences ->
        preferences[PreferencesKeys.APP_LANGUAGE]
    }

    override suspend fun updateLanguage(language: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKeys.APP_LANGUAGE] = language
        }
    }
}
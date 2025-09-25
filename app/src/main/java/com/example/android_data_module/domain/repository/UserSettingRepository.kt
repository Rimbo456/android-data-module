package com.example.android_data_module.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserSettingRepository {
    val languageFlow: Flow<String?>
    suspend fun updateLanguage(language: String)
}
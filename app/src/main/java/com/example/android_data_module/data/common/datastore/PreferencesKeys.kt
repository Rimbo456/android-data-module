package com.example.android_data_module.data.common.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    // Key để lưu mã ngôn ngữ (ví dụ: "en", "vi")
    val APP_LANGUAGE = stringPreferencesKey("app_language")
}

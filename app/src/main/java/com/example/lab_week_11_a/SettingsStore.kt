package com.example.lab_week_11_a
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

// Create the data store
// The data store is stored in the file
// /data/data/com.example.lab_week_11_a/files/settingsStore
// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingsStore(private val context: Context) {
    // The key used to access the data in the data store
    companion object {
        val KEY_TEXT = stringPreferencesKey("key_text")
    }

    // The text flow is used to notify the view model when the text changes
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_TEXT] ?: ""
        }
    // Save the text to the data store
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }

}


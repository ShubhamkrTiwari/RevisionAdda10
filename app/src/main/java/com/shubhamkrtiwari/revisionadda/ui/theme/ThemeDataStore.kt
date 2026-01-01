package com.shubhamkrtiwari.revisionadda.ui.theme

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object ThemeDataStore {
    private val IS_DARK = booleanPreferencesKey("is_dark_theme")

    fun isDarkThemeFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[IS_DARK] ?: false }

    suspend fun setDarkTheme(context: Context, isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_DARK] = isDark
        }
    }
}

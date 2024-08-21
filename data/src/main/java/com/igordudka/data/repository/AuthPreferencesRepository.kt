package com.igordudka.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AuthPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object{
        val IS_LOGIN = booleanPreferencesKey("isLogin")
        val TOKEN = stringPreferencesKey("token")
        val ROLE = stringPreferencesKey("role")
    }

    suspend fun saveRolePreference(value: String){
        dataStore.edit {
            it[ROLE] = value
        }
    }
    suspend fun saveLoginPreference(value: Boolean){
        dataStore.edit {
            it[IS_LOGIN] = value
        }
    }
    suspend fun saveTokenPreference(value: String){
        dataStore.edit {
            it[TOKEN] = value
        }
    }


    val role: Flow<String> = dataStore.data.map {
        it[ROLE] ?: ""
    }
    val isLogin: Flow<Boolean> = dataStore.data.map {
        it[IS_LOGIN] ?: false
    }
    val token: Flow<String> = dataStore.data.map {
        it[TOKEN] ?: ""
    }

}
package com.igordudka.tmate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.igordudka.data.network.BASE_URL
import com.igordudka.data.network.api.MainApi
import com.igordudka.data.repository.AuthPreferencesRepository
import com.igordudka.data.repository.AuthRepository
import com.igordudka.data.repository.FeedRepository
import com.igordudka.data.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val AUTH_PREFERENCES_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = AUTH_PREFERENCES_NAME
)
private val gson: Gson = GsonBuilder()
    .setLenient()
    .create()
private val mainApi: MainApi = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(MainApi::class.java)


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAuthRepository() : AuthRepository{
        return AuthRepository(mainApi)
    }

    @Provides
    @Singleton
    fun providesTeamsRepository() : TeamRepository{
        return TeamRepository(mainApi)
    }

    @Provides
    @Singleton
    fun providesFeedRepository() : FeedRepository{
        return FeedRepository(mainApi)
    }

    @Provides
    @Singleton
    fun providesAuthPreferencesRepository(@ApplicationContext context: Context) : AuthPreferencesRepository{
        return AuthPreferencesRepository(context.dataStore)
    }
}
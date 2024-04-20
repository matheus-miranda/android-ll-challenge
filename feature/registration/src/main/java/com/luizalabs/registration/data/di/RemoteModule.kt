package com.luizalabs.registration.data.di

import com.luizalabs.registration.data.remote.service.CitiesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideCityApi(retrofit: Retrofit): CitiesApi {
        return retrofit.create(CitiesApi::class.java)
    }
}

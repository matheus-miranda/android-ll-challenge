package com.luizalabs.delivery.di

import com.luizalabs.delivery.BuildConfig
import com.luizalabs.network.BuildType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @BuildType
    fun provideBuildType(): String {
        return BuildConfig.BUILD_TYPE
    }
}

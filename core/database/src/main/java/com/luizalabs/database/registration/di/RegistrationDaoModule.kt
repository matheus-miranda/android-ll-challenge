package com.luizalabs.database.registration.di

import com.luizalabs.database.AppDatabase
import com.luizalabs.database.registration.dao.DeliveryFormDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RegistrationDaoModule {
    @Provides
    fun provideDeliveryFormDao(
        database: AppDatabase,
    ): DeliveryFormDao = database.deliveryFormDao()
}

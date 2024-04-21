package com.luizalabs.registration.data.di

import com.luizalabs.registration.data.local.DeliveryRoomDataSource
import com.luizalabs.registration.data.repository.DeliveryRepositoryImpl
import com.luizalabs.registration.domain.repository.DeliveryLocalDataSource
import com.luizalabs.registration.domain.repository.DeliveryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeliveryRepositoryModule {

    @Binds
    abstract fun bindLocalDataSource(dataSource: DeliveryRoomDataSource): DeliveryLocalDataSource

    @Binds
    abstract fun bindDeliveryRepository(repositoryImpl: DeliveryRepositoryImpl): DeliveryRepository
}

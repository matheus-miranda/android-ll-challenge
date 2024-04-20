package com.luizalabs.registration.data.di

import com.luizalabs.registration.data.remote.CityRetrofitDataSource
import com.luizalabs.registration.data.repository.CityRepositoryImpl
import com.luizalabs.registration.domain.repository.CityRemoteDataSource
import com.luizalabs.registration.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CityRepositoryModule {

    @Binds
    abstract fun bindRemoteDataSource(dataSource: CityRetrofitDataSource): CityRemoteDataSource

    @Binds
    abstract fun bindCityRepository(repository: CityRepositoryImpl): CityRepository
}

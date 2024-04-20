package com.luizalabs.registration.data.repository

import com.luizalabs.registration.domain.Either
import com.luizalabs.registration.domain.error.Error
import com.luizalabs.registration.domain.model.City
import com.luizalabs.registration.domain.repository.CityRemoteDataSource
import com.luizalabs.registration.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityRemoteDataSource,
) : CityRepository {

    override suspend fun getCitiesByState(state: String): Either<List<City>, Error> {
        return dataSource.getCitiesByState(state)
    }
}

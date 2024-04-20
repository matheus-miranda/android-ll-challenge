package com.luizalabs.registration.domain.repository

import com.luizalabs.registration.domain.Either
import com.luizalabs.registration.domain.error.Error
import com.luizalabs.registration.domain.model.City

interface CityRemoteDataSource {
    suspend fun getCitiesByState(state: String): Either<List<City>, Error>
}

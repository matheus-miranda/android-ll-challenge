package com.luizalabs.registration.data.remote

import com.luizalabs.registration.data.remote.response.toModel
import com.luizalabs.registration.data.remote.service.CitiesApi
import com.luizalabs.registration.domain.Either
import com.luizalabs.registration.domain.error.Error
import com.luizalabs.registration.domain.error.NetworkError
import com.luizalabs.registration.domain.model.City
import com.luizalabs.registration.domain.repository.CityRemoteDataSource
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class CityRetrofitDataSource @Inject constructor(
    private val api: CitiesApi,
) : CityRemoteDataSource {

    override suspend fun getCitiesByState(state: String): Either<List<City>, Error> {
        return try {
            val response = api.getCities(state)
            Either.Success(response.map { it.toModel() })
        } catch (e: UnknownHostException) {
            Either.Failure(NetworkError.NO_INTERNET)
        } catch (e: HttpException) {
            when (e.code()) {
                408 -> Either.Failure(NetworkError.REQUEST_TIMEOUT)
                413 -> Either.Failure(NetworkError.PAYLOAD_TOO_LARGE)
                else -> Either.Failure(NetworkError.UNKNOWN_ERROR)
            }
        } catch (e: IOException) {
            Either.Failure(NetworkError.CONNECTION_ERROR)
        } catch (e: JsonDataException) {
            Either.Failure(NetworkError.PARSE_ERROR)
        } catch (e: Exception) {
            Either.Failure(NetworkError.UNKNOWN_ERROR)
        }
    }
}

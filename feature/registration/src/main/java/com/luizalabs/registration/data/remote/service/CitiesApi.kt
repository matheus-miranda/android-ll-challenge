package com.luizalabs.registration.data.remote.service

import com.luizalabs.registration.data.remote.response.CityResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CitiesApi {

    @GET(CITY)
    suspend fun getCities(
        @Path("UF") state: String,
        @Query("orderBy") orderBy: String = "nome",
    ): List<CityResponse>

    companion object {
        private const val CITY = "localidades/estados/{UF}/municipios"
    }
}

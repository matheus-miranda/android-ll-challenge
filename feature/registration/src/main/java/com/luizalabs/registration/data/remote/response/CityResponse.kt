package com.luizalabs.registration.data.remote.response

import com.luizalabs.registration.domain.model.City
import com.squareup.moshi.Json

data class CityResponse(
    @field:Json(name = "nome") val name: String,
)

fun CityResponse.toModel() = City(
    name = name
)

package com.luizalabs.registration.data.remote.response

import com.luizalabs.registration.domain.model.City
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

fun CityResponse.toModel() = City(
    name = this.nome
)

@JsonClass(generateAdapter = true)
data class CityResponse(
    val id: Int?,
    @field:Json(name = "microrregiao") val microRegion: Microrregiao?,
    val nome: String,
    @field:Json(name = "regiao-imediata") val regiao_imediata: RegiaoImediata?,
)

@JsonClass(generateAdapter = true)
data class Microrregiao(
    val id: Int,
    val mesorregiao: Mesorregiao,
    val nome: String,
)

@JsonClass(generateAdapter = true)
data class RegiaoImediata(
    val id: Int,
    val nome: String,
    @field:Json(name = "regiao-intermediaria") val regiao_intermediaria: RegiaoIntermediaria,
)

@JsonClass(generateAdapter = true)
data class Mesorregiao(
    val UF: UF,
    val id: Int,
    val nome: String,
)

@JsonClass(generateAdapter = true)
data class UF(
    val id: Int,
    val nome: String,
    val regiao: Regiao,
    val sigla: String,
)

@JsonClass(generateAdapter = true)
data class Regiao(
    val id: Int,
    val nome: String,
    val sigla: String,
)

@JsonClass(generateAdapter = true)
data class RegiaoIntermediaria(
    val UF: UF,
    val id: Int,
    val nome: String,
)

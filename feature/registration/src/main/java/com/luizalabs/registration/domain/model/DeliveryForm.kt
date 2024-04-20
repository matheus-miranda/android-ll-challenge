package com.luizalabs.registration.domain.model

data class DeliveryForm(
    val id: Int,
    val parcelQuantity: Int,
    val deliveryDeadline: Long,
    val clientName: String,
    val clientCpf: String,
    val address: DeliveryAddress,
)

data class DeliveryAddress(
    val zipCode: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: String,
    val complement: String?,
)

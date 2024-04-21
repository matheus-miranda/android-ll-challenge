package com.luizalabs.registration.data.local.mapper

import com.luizalabs.database.registration.entity.DeliveryFormEntity
import com.luizalabs.registration.domain.model.DeliveryAddress
import com.luizalabs.registration.domain.model.DeliveryForm

fun DeliveryFormEntity.toModel() = DeliveryForm(
    id = id,
    parcelQuantity = parcelQuantity,
    deliveryDeadline = deliveryDeadline,
    clientName = clientName,
    clientCpf = clientCpf,
    address = DeliveryAddress(
        zipCode = zipCode,
        state = state,
        city = city,
        neighborhood = neighborhood,
        street = street,
        number = number,
        complement = complement
    )
)

fun List<DeliveryFormEntity>.toModel() = map {
    it.toModel()
}

fun DeliveryForm.toEntity() = DeliveryFormEntity(
    id = id,
    parcelQuantity = parcelQuantity,
    deliveryDeadline = deliveryDeadline,
    clientName = clientName,
    clientCpf = clientCpf,
    zipCode = address.zipCode,
    state = address.state,
    city = address.city,
    neighborhood = address.neighborhood,
    street = address.street,
    number = address.number,
    complement = address.complement
)

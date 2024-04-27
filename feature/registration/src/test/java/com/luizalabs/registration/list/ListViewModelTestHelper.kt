package com.luizalabs.registration.list

import com.luizalabs.registration.domain.model.DeliveryAddress
import com.luizalabs.registration.domain.model.DeliveryForm

object ListViewModelTestHelper {
    val form = DeliveryForm(
        id = 4942,
        parcelQuantity = 9527,
        deliveryDeadline = 1684,
        clientName = "Tommy Dixon",
        clientCpf = "similique",
        address = DeliveryAddress(
            zipCode = "38466",
            state = "Indiana",
            city = "Pallet Town",
            neighborhood = "posuere",
            street = "senserit",
            number = "libero",
            complement = null
        )
    )
}

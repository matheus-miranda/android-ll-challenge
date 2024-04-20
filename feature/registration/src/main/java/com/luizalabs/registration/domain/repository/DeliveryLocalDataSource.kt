package com.luizalabs.registration.domain.repository

import com.luizalabs.registration.domain.model.DeliveryForm
import kotlinx.coroutines.flow.Flow

interface DeliveryLocalDataSource {
    fun getForms(): Flow<List<DeliveryForm>>
    suspend fun saveForm(form: DeliveryForm)
    suspend fun getFormById(id: Int): DeliveryForm
    suspend fun deleteFormById(id: Int)
}

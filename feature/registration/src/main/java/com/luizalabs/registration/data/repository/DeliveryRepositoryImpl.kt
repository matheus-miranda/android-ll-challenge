package com.luizalabs.registration.data.repository

import com.luizalabs.registration.domain.model.DeliveryForm
import com.luizalabs.registration.domain.repository.DeliveryLocalDataSource
import com.luizalabs.registration.domain.repository.DeliveryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeliveryRepositoryImpl @Inject constructor(
    private val dataSource: DeliveryLocalDataSource,
) : DeliveryRepository {

    override fun getForms(): Flow<List<DeliveryForm>> {
        return dataSource.getForms()
    }

    override suspend fun saveForm(form: DeliveryForm) {
        dataSource.saveForm(form)
    }

    override suspend fun getFormById(id: Int): DeliveryForm {
        return dataSource.getFormById(id)
    }

    override suspend fun deleteFormById(id: Int) {
        dataSource.deleteFormById(id)
    }
}

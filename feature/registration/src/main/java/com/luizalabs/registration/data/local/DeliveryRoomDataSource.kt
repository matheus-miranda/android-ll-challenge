package com.luizalabs.registration.data.local

import com.luizalabs.database.registration.dao.DeliveryFormDao
import com.luizalabs.registration.data.local.mapper.toEntity
import com.luizalabs.registration.data.local.mapper.toModel
import com.luizalabs.registration.domain.model.DeliveryForm
import com.luizalabs.registration.domain.repository.DeliveryLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeliveryRoomDataSource @Inject constructor(
    private val dao: DeliveryFormDao,
) : DeliveryLocalDataSource {

    override fun getForms(): Flow<List<DeliveryForm>> {
        return dao.getAllForms().map { it.toModel() }
    }

    override suspend fun saveForm(form: DeliveryForm) {
        dao.saveForm(form.toEntity())
    }

    override suspend fun getFormById(id: Int): DeliveryForm {
        return dao.getFormById(id).toModel()
    }

    override suspend fun deleteFormById(id: Int) {
        dao.deleteFormById(id)
    }
}

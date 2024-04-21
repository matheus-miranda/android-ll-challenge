package com.luizalabs.database.registration.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.luizalabs.database.registration.entity.DeliveryFormEntity
import com.luizalabs.database.registration.entity.RegistrationConstants.DELIVERY_FORM_COLUMN_ID
import com.luizalabs.database.registration.entity.RegistrationConstants.DELIVERY_FORM_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryFormDao {
    @Query("SELECT * FROM $DELIVERY_FORM_TABLE_NAME ORDER BY $DELIVERY_FORM_COLUMN_ID DESC")
    fun getAllForms(): Flow<List<DeliveryFormEntity>>

    @Upsert
    suspend fun saveForm(deliveryForm: DeliveryFormEntity)

    @Query("SELECT * FROM $DELIVERY_FORM_TABLE_NAME WHERE $DELIVERY_FORM_COLUMN_ID = :id")
    suspend fun getFormById(id: Int): DeliveryFormEntity

    @Query("DELETE FROM $DELIVERY_FORM_TABLE_NAME WHERE $DELIVERY_FORM_COLUMN_ID = :id")
    suspend fun deleteFormById(id: Int)
}

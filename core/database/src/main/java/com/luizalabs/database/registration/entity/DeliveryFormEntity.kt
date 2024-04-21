package com.luizalabs.database.registration.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RegistrationConstants.DELIVERY_FORM_TABLE_NAME)
data class DeliveryFormEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_ID)
    var id: Int = 0,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_PARCEL_QUANTITY)
    val parcelQuantity: Int,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_DELIVERY_DEADLINE)
    val deliveryDeadline: Long,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_CLIENT_NAME)
    val clientName: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_CLIENT_CPF)
    val clientCpf: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_ZIP_CODE)
    val zipCode: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_STATE)
    val state: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_CITY)
    val city: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_NEIGHBORHOOD)
    val neighborhood: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_STREET)
    val street: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_NUMBER)
    val number: String,

    @ColumnInfo(RegistrationConstants.DELIVERY_FORM_COLUMN_COMPLEMENT)
    val complement: String?,
)

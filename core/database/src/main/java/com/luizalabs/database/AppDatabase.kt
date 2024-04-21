package com.luizalabs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luizalabs.database.registration.dao.DeliveryFormDao
import com.luizalabs.database.registration.entity.DeliveryFormEntity

@Database(entities = [DeliveryFormEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun deliveryFormDao(): DeliveryFormDao
}

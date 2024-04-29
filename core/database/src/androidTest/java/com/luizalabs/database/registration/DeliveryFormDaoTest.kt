package com.luizalabs.database.registration

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.luizalabs.database.AppDatabase
import com.luizalabs.database.registration.DeliveryFormDaoTestHelper.deliveryFormEntity
import com.luizalabs.database.registration.dao.DeliveryFormDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class DeliveryFormDaoTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var dao: DeliveryFormDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.deliveryFormDao()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        db.close()
    }

    @Test
    fun saveFormAndReadInList() = runTest {
        dao.saveForm(deliveryFormEntity)

        val deliveries = dao.getAllForms().first()
        assertTrue("Item not contained in DB", deliveries.contains(deliveryFormEntity))
    }

    @Test
    fun deleteFromDb() = runTest {
        dao.saveForm(deliveryFormEntity)
        dao.deleteFormById(deliveryFormEntity.id)

        val deliveries = dao.getAllForms().first()
        assertTrue("DB Contains form", deliveries.contains(deliveryFormEntity).not())
    }

    @Test
    fun getFormById() = runTest {
        dao.saveForm(deliveryFormEntity)
        val actual = dao.getFormById(deliveryFormEntity.id)

        val expected = dao.getAllForms().first()
        assertEquals(expected, listOf(actual))
    }
}

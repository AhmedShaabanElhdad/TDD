package com.example.datalayer.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.datalayer.local.dao.ShoppingItemDao
import com.example.datalayer.model.entity.ShoppingItem
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShopingItemDaoTest {


    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    lateinit var database: ShoppingItemDatabase
    lateinit var dao: ShoppingItemDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.shoppingItemDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun insertShoppingItem() = runBlocking {

        val item = ShoppingItem(
            id = 1,
            name = "car",
            imageUrl = "",
            price = 150000f,
            amount = 1
        )
        dao.addItem(item)

        val allUser = dao.getAllItem()
        assertThat(allUser).contains(item)
    }


    @Test
    fun DeleteOrder() = runBlocking {

        val item = ShoppingItem(
            id = 1,
            name = "car",
            imageUrl = "",
            price = 150000f,
            amount = 1
        )
        dao.addItem(item)
        dao.deleteItem(item)

        val allUser = dao.getAllItem()
        assertThat(allUser).doesNotContain(item)
    }

    @Test
    fun getTotalPrice() = runBlocking {
        val items = listOf<ShoppingItem>(
            ShoppingItem(id = 1, name = "car", imageUrl = "", price = 150000f, amount = 1),
            ShoppingItem(id = 2, name = "wash Machine", imageUrl = "", price = 4000f, amount = 2),
            ShoppingItem(id = 3, name = "TV", imageUrl = "", price = 6000f, amount = 3)
        )
        dao.addItems(items)

        val totalAmount = dao.getTotalPrice()

        assertThat(totalAmount).isEqualTo(1*150000f + 2*4000f+3*6000f)

    }

}
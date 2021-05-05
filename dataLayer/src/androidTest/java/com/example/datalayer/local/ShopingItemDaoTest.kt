package com.example.datalayer.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.datalayer.local.dao.ShoppingItemDao
import com.example.datalayer.model.entity.ShoppingItemDTO
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShopingItemDaoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingItemDatabase
    lateinit var dao: ShoppingItemDao


    @Before
    fun setup() {
        hiltAndroidRule.inject()
        dao = database.shoppingItemDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun insertShoppingItem() = runBlocking {

        val item = ShoppingItemDTO(
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

        val item = ShoppingItemDTO(
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
        val items = listOf<ShoppingItemDTO>(
            ShoppingItemDTO(id = 1, name = "car", imageUrl = "", price = 150000f, amount = 1),
            ShoppingItemDTO(
                id = 2,
                name = "wash Machine",
                imageUrl = "",
                price = 4000f,
                amount = 2
            ),
            ShoppingItemDTO(id = 3, name = "TV", imageUrl = "", price = 6000f, amount = 3)
        )
        dao.addItems(items)

        val totalAmount = dao.getTotalPrice()

        assertThat(totalAmount).isEqualTo(1 * 150000f + 2 * 4000f + 3 * 6000f)

    }

}
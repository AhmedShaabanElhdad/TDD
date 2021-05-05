package com.example.presentation.feature.shopping.vm

import com.example.domain.core.MAX_CHARACTER_LENGTH
import com.example.domain.core.MAX_PRICE_LENGTH
import com.example.domain.core.Status
import com.example.domain.usecase.ShoppingUseCase
import com.example.presentation.getOrAwaitValue
import com.google.common.truth.Truth
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.presentation.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    lateinit var viewmodel: ShoppingViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewmodel = ShoppingViewModel(ShoppingUseCase(FakeItemRepository(), FakeImageRepository()))
    }


    @Test
    fun `insert shopping item with empty field return error`() {
        viewmodel.insertItemShop("", "20.0f", "2")

        val value = viewmodel.inseredItemShoppingStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with Too long field  name return error`() {
        val string = buildString {
            for (i in 1..MAX_CHARACTER_LENGTH + 1) {
                append(i)
            }
        }
        viewmodel.insertItemShop(string, "20.0f", "2")

        val value = viewmodel.inseredItemShoppingStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with Too long price return error`() {
        val string = buildString {
            for (i in 1..MAX_PRICE_LENGTH + 1) {
                append(i)
            }
        }
        viewmodel.insertItemShop("name", string, "2")

        val value = viewmodel.inseredItemShoppingStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with Too long amount return error`() {

        val amount = "9999999999999999999999"

        viewmodel.insertItemShop("name", "20f", amount)

        val value = viewmodel.inseredItemShoppingStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid data return success`() {

        viewmodel.insertItemShop("name", "20f", "2")

        val value = viewmodel.inseredItemShoppingStatus.getOrAwaitValue()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `insert shopping item with Too long amount Empty ImageUrl`() {

        viewmodel.setCurrentImageUrl("https://")

        viewmodel.insertItemShop("name", "20f", "20")

        val value = viewmodel.currentImage.getOrAwaitValue()

        Truth.assertThat(value).isEmpty()
    }

    @Test
    fun `setImage Url return Success to Image`() {

        val url  = "https://"

        viewmodel.setCurrentImageUrl(url)

        val value = viewmodel.currentImage.getOrAwaitValue()

        Truth.assertThat(value).isEqualTo(url)
    }

}
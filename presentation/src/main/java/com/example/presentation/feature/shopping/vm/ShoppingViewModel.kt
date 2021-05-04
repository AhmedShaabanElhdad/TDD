package com.example.presentation.feature.shopping.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.MAX_CHARACTER_LENGTH
import com.example.domain.core.MAX_PRICE_LENGTH
import com.example.presentation.core.Event
import com.example.domain.core.Resource
import com.example.domain.model.MyImage
import com.example.domain.model.ShoppingItem
import com.example.domain.usecase.PickupImageUseCase
import com.example.domain.usecase.ShoppingUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class ShoppingViewModel @ViewModelInject constructor(
    private val shoppingUseCase: ShoppingUseCase
):ViewModel() {

    suspend fun getAllItems() = shoppingUseCase.getAllItems()
    suspend fun calculatePrice() = shoppingUseCase.calculatePrice()

    private val _images = MutableLiveData<Event<Resource<List<MyImage>>>>()
    val images: LiveData<Event<Resource<List<MyImage>>>> = _images

    private val _currentImage = MutableLiveData<String>()
    val currentImage: LiveData<String> = _currentImage

    private val _inseredItemShoppingStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val inseredItemShoppingStatus: LiveData<Event<Resource<ShoppingItem>>> = _inseredItemShoppingStatus

    fun setCurrentImageUrl(imageUrl:String){
        _currentImage.postValue(imageUrl)
    }

    fun insertItemInDao(shoppingItem: ShoppingItem) = viewModelScope.launch {
        shoppingUseCase.addItem(shoppingItem)
    }

    fun deleteItemFromDao(shoppingItem: ShoppingItem) = viewModelScope.launch {
        shoppingUseCase.deleteItem(shoppingItem)
    }

    fun insertItemShop(name:String, price:String, amountString:String){
        if (name.isEmpty()||price.isEmpty()||amountString.isEmpty()){
            _inseredItemShoppingStatus.postValue(Event(Resource.error("the shopping Fields must not be Empty ",null)))
            return
        }
        if (name.length> MAX_CHARACTER_LENGTH){
            _inseredItemShoppingStatus.postValue(Event(Resource.error("Name Must not exceed ${MAX_CHARACTER_LENGTH} characters",null)))
            return
        }
        if (price.length> MAX_PRICE_LENGTH){
            _inseredItemShoppingStatus.postValue(Event(Resource.error("Price Must not exceed ${MAX_PRICE_LENGTH} characters",null)))
            return
        }

        val amount = try {
            amountString.toInt()
        }catch (e:Exception){
            _inseredItemShoppingStatus.postValue(Event(Resource.error("Please Enter Valid Amount",null)))
            return
        }

        val shoppingItem = ShoppingItem(name = name,amount = amount,price = price.toFloat(),imageUrl = _currentImage.value?:"")
        insertItemInDao(shoppingItem)
        setCurrentImageUrl("")
        _inseredItemShoppingStatus.postValue(Event(Resource.success(shoppingItem)))

    }

    fun searchForImage(searchImage:String){
        if (searchImage.isNullOrEmpty())
            return
        _images.value = Event(Resource.loading(null))

        viewModelScope.launch {
            val response = shoppingUseCase.search(searchImage)
            _images.value = Event(response)
        }
    }

}
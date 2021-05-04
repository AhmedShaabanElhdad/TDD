package com.example.presentation.feature.addShopping.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.AddShoppingUseCase

class AddShoppingViewModel @ViewModelInject constructor(
    val addShoppingUseCase: AddShoppingUseCase
) : ViewModel() {



}
package com.example.domain.model



data class ShoppingItem(
    val id:Int = 0,
    val name:String = "",
    val imageUrl:String = "",
    val price:Float = 0f,
    val amount:Int=0,
)
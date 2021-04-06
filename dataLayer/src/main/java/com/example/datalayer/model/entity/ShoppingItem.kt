package com.example.datalayer.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ShoppingItem")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val imageUrl:String,
    val price:Float,
    val amount:Int,
)
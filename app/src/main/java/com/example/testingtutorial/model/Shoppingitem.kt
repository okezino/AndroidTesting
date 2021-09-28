package com.example.testingtutorial.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item")
data class ShoppingItem(
    val name : String,
    val amount : Int,
    val price : Float,
    val imageUrl : String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null

)

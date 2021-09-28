package com.example.testingtutorial.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun  deleteShoppingItem(shoppingItem: ShoppingItem)

    /**
     * I don't need  to add suspend function because LiveData is already working
     * Asynchronously and does not need to been in anoda asynchronous environment
     */
    @Query("SELECT * FROM shopping_item")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount ) FROM shopping_item ")
    fun observeTablePrice() : LiveData<Float>

}
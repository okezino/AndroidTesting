package com.example.testingtutorial.repository

import androidx.lifecycle.LiveData
import com.example.testingtutorial.model.ShoppingItem
import com.example.testingtutorial.remote.ImageResponse
import com.example.testingtutorial.remote.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItem() : LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery : String) : Resource<ImageResponse>


}
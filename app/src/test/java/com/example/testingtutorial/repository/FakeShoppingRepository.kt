package com.example.testingtutorial.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testingtutorial.model.ShoppingItem
import com.example.testingtutorial.remote.ImageResponse
import com.example.testingtutorial.remote.Resource

class FakeShoppingRepository : ShoppingRepository {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItem = MutableLiveData<List<ShoppingItem>>(shoppingItems)

    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    fun refreshLiveData(){
        observableShoppingItem.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }
    private fun getTotalPrice(): Float{

        return shoppingItems.sumByDouble { it.amount.toDouble() * it.price }.toFloat()


    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {

        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()

    }

    override fun observeAllShoppingItem(): LiveData<List<ShoppingItem>> {
        return observableShoppingItem

    }

    override fun observeTotalPrice(): LiveData<Float> {

        return observableTotalPrice

    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        if(shouldReturnNetworkError){
            return Resource.error("Failed Call", null)
        }else {
            return  Resource.success(ImageResponse(listOf(), 0, 1))
        }

    }

}
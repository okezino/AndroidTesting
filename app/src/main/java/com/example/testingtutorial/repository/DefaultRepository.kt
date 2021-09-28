package com.example.testingtutorial.repository

import androidx.lifecycle.LiveData
import com.example.testingtutorial.model.ShoppingDao
import com.example.testingtutorial.model.ShoppingItem
import com.example.testingtutorial.model.ShoppingItemDB
import com.example.testingtutorial.remote.ImageResponse
import com.example.testingtutorial.remote.PixableApi
import com.example.testingtutorial.remote.Resource
import retrofit2.Response
import java.lang.reflect.Constructor
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val shoppingDao: ShoppingDao, private val apiCall : PixableApi
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
      shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
     shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItem(): LiveData<List<ShoppingItem>> {
       return  shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
       return shoppingDao.observeTablePrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {

        try{

            val response = apiCall.searchForImage(imageQuery)

            if(response.isSuccessful){
                response.body()?.let {
                    return Resource.success(it)
                } ?: return  Resource.error("Unknown Error", null)

            }else {

                return  Resource.error("Unknown Error", null)
            }
        }catch (e : Exception){
            return  Resource.error("Could not react the serve, Check internet connection", null)
        }

    }
}
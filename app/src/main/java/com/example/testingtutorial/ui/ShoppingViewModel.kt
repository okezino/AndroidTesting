package com.example.testingtutorial.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingtutorial.model.ShoppingItem
import com.example.testingtutorial.remote.ImageResponse
import com.example.testingtutorial.remote.Resource
import com.example.testingtutorial.repository.DefaultRepository
import com.example.testingtutorial.repository.ShoppingRepository
import com.example.testingtutorial.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    var shoppingItems = repository.observeAllShoppingItem()
    var totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images : LiveData<Event<Resource<ImageResponse>>>
    get() = _images

    private val _currentImages = MutableLiveData<String>()
    val CurrentImages : LiveData<String>
        get() = _currentImages

    private val _insertShoppingListStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingListStatus : LiveData<Event<Resource<ShoppingItem>>>
        get() = _insertShoppingListStatus


    fun setCurImage(url : String){
        _currentImages.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem){
     viewModelScope.launch(Dispatchers.IO) {
         repository.deleteShoppingItem(shoppingItem)
     }
    }

    fun insertShoppingItemIntoDB(shoppingItem: ShoppingItem){
        viewModelScope.launch {
            repository.insertShoppingItem(shoppingItem)
        }
    }

    fun insertShoppingItem(name: String, amount : String, price : String){

    }

    fun searchImage(url : String){

    }


}
package com.example.testingtutorial.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @SmallTest this is when we want to run unit test on the Android
 * @MediumTest thus is when we want to run intergration test on the Android
 * @LargeTest
 * @Error This job has not completed yet
 * @instantTaskExecutorRule this is use to explicitly telling JUNIT to run all test in a synchronous task
 *
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    private  lateinit var dataBase : ShoppingItemDB
    private lateinit var dao : ShoppingDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ShoppingItemDB::class.java
        ).allowMainThreadQueries().build()

        dao = dataBase.shoppingDao()
    }

    @After
    fun tearDown(){
        dataBase.close()
    }


    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("banana",1,4f,"url", id = 1)
        dao.insertShoppingItem(shoppingItem)

        val allShoppingItem = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItem.contains(shoppingItem))

    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("banana",1,4f,"url", id = 1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)
        val allShoppingItem = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItem).doesNotContain(shoppingItem)

    }

    @Test
    fun observeTotalPrice() = runBlockingTest {
        val shoppingItem = ShoppingItem("banana",1,10f,"url", id = 1)
        val shoppingItem2 = ShoppingItem("plantain",1,20f,"url", id = 2)
        val shoppingItem3 = ShoppingItem("guava",1,30f,"url", id = 3)
        dao.insertShoppingItem(shoppingItem)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        val totalPriceSum = dao.observeTablePrice().getOrAwaitValue()

        assertThat(totalPriceSum).isEqualTo(60f)

    }
}
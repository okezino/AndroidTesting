package com.example.testingtutorial.di


import android.content.Context
import androidx.room.Room
import com.example.testingtutorial.model.ShoppingDao
import com.example.testingtutorial.model.ShoppingItemDB
import com.example.testingtutorial.remote.PixableApi
import com.example.testingtutorial.repository.DefaultRepository
import com.example.testingtutorial.repository.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideShoppingRoomDataBase(@ApplicationContext context: Context) : ShoppingItemDB{
        return  Room.databaseBuilder(context, ShoppingItemDB::class.java, "SHOPPING_DB").build()
    }



    @Singleton
    @Provides
    fun provideDefaultRepository(
        dao : ShoppingDao, api :PixableApi
    )  = DefaultRepository(dao , api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideDataBaseDao(database: ShoppingItemDB) : ShoppingDao{
        return database.shoppingDao()
    }

    @Singleton
    @Provides
    fun provideRetrofitCaller() :PixableApi{
       return  Retrofit.Builder().baseUrl("https://pixabay.com").addConverterFactory(GsonConverterFactory.create())
            .build().create(PixableApi::class.java)
    }
}
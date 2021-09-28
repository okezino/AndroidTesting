package com.example.testingtutorial.remote

import com.example.testingtutorial.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixableApi {

    @GET("/api/")
    suspend fun searchForImage(@Query("q") SearchQuery: String,
                               @Query("key") apikey : String = BuildConfig.API_KEY ):
            Response<ImageResponse>
}
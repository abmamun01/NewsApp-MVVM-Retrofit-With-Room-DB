package com.example.newsappmvvmusingkotlin.ui.API

import com.example.newsappmvvmusingkotlin.ui.Models.NewsResponse
import com.example.newsappmvvmusingkotlin.ui.Utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    //Jehetu amra data get korbo tai "GET" request Use korbo
    @GET("v2/top-headlines")
    //Use Corutines
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>


    //Jehetu amra data get korbo tai "GET" request Use korbo
    @GET("v2/everything")
    //Use Corutines
    suspend fun searchForNews(
        @Query("country")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>


}
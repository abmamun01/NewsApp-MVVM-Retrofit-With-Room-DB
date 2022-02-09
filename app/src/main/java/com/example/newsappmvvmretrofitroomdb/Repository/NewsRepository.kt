package com.example.newsappmvvmusingkotlin.Repository

import com.example.newsappmvvmusingkotlin.ui.API.RetrofitInstance
import com.example.newsappmvvmusingkotlin.ui.DB.ArticleDatabase

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}
package com.example.newsappmvvmusingkotlin.Repository

import com.example.newsappmvvmusingkotlin.ui.API.RetrofitInstance
import com.example.newsappmvvmusingkotlin.ui.DB.ArticleDatabase
import com.example.newsappmvvmusingkotlin.ui.Models.Article

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)


    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}
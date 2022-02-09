package com.example.newsappmvvmusingkotlin.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsappmvvmusingkotlin.ui.Models.Article

@Dao
interface Articles_Dao {

    //Onconflict r kaj hocce amra j data insert korte cacci database seta jodi already insert kora thake tahole ata replace kore dibe
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long


    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>


    @Delete
    suspend fun deleteArticle(article: Article)




}
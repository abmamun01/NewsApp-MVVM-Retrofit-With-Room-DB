package com.example.newsappmvvmusingkotlin.ui.DB

import android.content.Context
import androidx.room.*
import com.example.newsappmvvmusingkotlin.DB.Articles_Dao
import com.example.newsappmvvmusingkotlin.ui.Models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): Articles_Dao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null

        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =

            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()


    }
}
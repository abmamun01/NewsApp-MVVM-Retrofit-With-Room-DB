package com.example.newsappmvvmusingkotlin.ui.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "articles")
data class Article(

    //Sob gula te Id r proyojon nai karon amra sob gula news room db te save kormuna just jeta icca seta tai
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,

    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String

) : Serializable
package com.example.newsappmvvmusingkotlin.ui.DB

import androidx.room.TypeConverter
import com.example.newsappmvvmusingkotlin.ui.Models.Source

class Converters {

    //room hocce String and interger convert kore ovvosto tai Kono class k convert korte hole oi class k string e nite hobe tai Type Converter proyojon
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name:String): Source {
        return Source(name,name)
    }
}
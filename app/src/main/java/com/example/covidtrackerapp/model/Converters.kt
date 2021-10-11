package com.example.covidtrackerapp.model

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromSource(source: Source) =  source.name


    @TypeConverter
    fun toSource(name: String) =  Source(name, name)

}
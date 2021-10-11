package com.example.covidtrackerapp.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "country_details")
data class CountryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "country_name")
    val name: String,
    @ColumnInfo(name="country_flag")
    val image: String,

)


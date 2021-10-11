package com.example.covidtrackerapp.model.remote.covidApi

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CovidResponse(
    val global: Global,
    val Countries: List<CountryDescription>,
    val Date: String
)

@Entity(tableName = "global_cases")
data class Global(
   val NewConfirmed :Int,
   val TotalConfirmed :Int,
   val NewDeaths :Int,
   val TotalDeaths :Int,
   val NewRecovered :Int,
   val TotalRecovered :Int,
   val Date :String,
)

@Entity(tableName = "country_desc")
data class CountryDescription(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val Country: String,
    val CountryCode: String,
    val NewConfirmed: Int,
    val TotalConfirmed: Int,
    val NewDeaths: Int,
    val TotalDeaths: Int,
    val TotalRecovered: Int,
    val Date: String

)
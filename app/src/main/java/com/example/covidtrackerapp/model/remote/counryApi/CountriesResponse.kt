package com.example.covidtrackerapp.model


//https://restcountries.com/v3/all
class CountriesResponse : ArrayList<CountryItem>()

data class CountryItem(

    val capital: List<String>,
    val flag: String,
    val flags: List<String>,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val region: String,
    val status: String,
    val subregion: String,
    val tld : List<String>

    )

data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
)

data class Name(
    val common: String,
    val official: String
)
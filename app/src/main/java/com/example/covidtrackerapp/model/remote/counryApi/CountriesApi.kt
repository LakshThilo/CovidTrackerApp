package com.example.covidtrackerapp.model.remote.counryApi

import com.example.covidtrackerapp.model.CountriesResponse
import com.example.covidtrackerapp.util.Constants.Companion.BASE_URL_COUNTRIES
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://restcountries.com/v3/all


interface CountriesApi {

    @GET("v3/all")
    suspend fun searchForCountries() : CountriesResponse


    companion object {

        fun getApi(): CountriesApi {

            return  Retrofit.Builder()
                .baseUrl(BASE_URL_COUNTRIES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountriesApi::class.java)
        }
    }
}
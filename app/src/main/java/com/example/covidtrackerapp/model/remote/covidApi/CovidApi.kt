package com.example.covidtrackerapp.model.remote.covidApi

import com.example.covidtrackerapp.util.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://api.covid19api.com/summary

//https://api.covid19api.com/country/south-africa/status/death
//https://api.covid19api.com/country/south-africa/status/confirmed

interface CovidApi {

    @GET("summary")
    suspend fun getCovidDetails() : Response<CovidResponse>

}


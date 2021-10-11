package com.example.covidtrackerapp.repository

import com.example.covidtrackerapp.model.local.CovidRoomDatabase
import com.example.covidtrackerapp.model.remote.covidApi.RetrofitInstanceForCovid

class CovidCasesRepository(val db: CovidRoomDatabase) {

    suspend fun getAllCovidCases() = RetrofitInstanceForCovid.covidApi.getCovidDetails()
}
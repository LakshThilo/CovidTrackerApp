package com.example.covidtrackerapp.repository


/* this interface going behave as a Dependency Inversion Principle */
interface ICountryRepository {

    suspend fun getUIData() :UIState
}
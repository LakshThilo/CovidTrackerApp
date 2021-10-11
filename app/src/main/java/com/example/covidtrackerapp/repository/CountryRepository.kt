package com.example.covidtrackerapp.repository

import com.example.covidtrackerapp.model.CountriesResponse
import com.example.covidtrackerapp.model.remote.counryApi.CountriesApi

/**
 *  Database logic also need to implement here,
 *  - view model only going to request data
 *
 *  - where the data going from for that  repository responsible to define that
 *      - you can implement cache ot offline everything gonna be here
 * */
class CountryRepository: ICountryRepository {


    private val networkService: CountriesApi by lazy {
        CountriesApi.getApi()
    }

    override suspend fun getUIData(): UIState {
        val response = networkService.searchForCountries()  // calling coroutine inside the another coroutine

        return if(response == null)
            UIState.Failure("Server Error")
        else
            UIState.Response(response)
    }
}

/* UI State
    Lifecycle of data

*   Response
    Failure
    Loading
* */
// in call/observer we got thi response/failure  --->>> this sealed class should e in separate class
sealed class UIState{

    data class Response(val data: CountriesResponse) :UIState()
    data class Failure(val errorMessage: String) :UIState()
    object Loading: UIState()
}
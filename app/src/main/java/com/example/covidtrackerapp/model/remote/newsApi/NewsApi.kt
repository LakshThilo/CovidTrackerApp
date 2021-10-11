package com.example.covidtrackerapp.model.remote.newsApi

import com.example.covidtrackerapp.model.NewsResponse
import com.example.covidtrackerapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//https://newsapi.org/v2/everything?q=covid&apiKey=4524b5b857cc4651bc669c73b6b5bea8
interface NewsApi {

    @GET("v2/everything")
    suspend fun getAllCovidNews(
        @Query("q") searchQuery: String = "covid",
      //  @Query("country") countryCode: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsResponse>
}
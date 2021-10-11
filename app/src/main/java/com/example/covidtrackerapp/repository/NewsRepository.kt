package com.example.covidtrackerapp.repository

import com.example.covidtrackerapp.model.remote.newsApi.RetrofitInstanceForNews
import com.example.covidtrackerapp.model.local.MainRoomDatabase

// we will need that DB to access the function of our database, and this repo also need to access
// Apis but we gonna get that retrofit
class NewsRepository(val db: MainRoomDatabase) {

    suspend fun getLatestNews(searchQuery : String, pageNumbers: Int) =
     RetrofitInstanceForNews.newsApi.getAllCovidNews(searchQuery,pageNumbers)

    /*suspend fun getLatestNews(countryCode: String, pageNumber: Int) =
        RetrofitInstanceForNews.newsApi.getAllCovidNews(countryCode,pageNumber)*/
}
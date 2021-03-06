package com.example.covidtrackerapp.model.remote.covidApi

import com.example.covidtrackerapp.model.remote.newsApi.NewsApi
import com.example.covidtrackerapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceForCovid {

    companion object {

        private val retrofitNews by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_COVID_CASES)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val covidApi by lazy {
            retrofitNews.create(CovidApi::class.java)
        }

    }
}
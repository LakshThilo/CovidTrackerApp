package com.example.covidtrackerapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidtrackerapp.model.NewsResponse
import com.example.covidtrackerapp.repository.*
import com.example.covidtrackerapp.util.ResourceStatus
import kotlinx.coroutines.*
import retrofit2.Response


/** ViewModel is only going to receive one source of data
 * */

private const val TAG = "Country ViewModel"

/* as you may know we can not use constructor parameter by default for our view model if we want to do that we need to create what is called
* viewModel provider factory to define how our view model should be created */
class MainViewModel(val newsRepository: NewsRepository) : ViewModel() {


      private val coroutineScope = CoroutineScope(Dispatchers.IO)

        private val countryRepository :ICountryRepository by lazy {

        CountryRepository() //hard coupling here --> this should provide outside the class -- use dependency injection--- Dagger/Coin() for constructor injection
    }


    /* create live data */
    private val _country = MutableLiveData<UIState>()
    val country: LiveData<UIState> get() = _country

    // for news
    val globalCovidNews : MutableLiveData<ResourceStatus<NewsResponse>> = MutableLiveData()
    var globalNewsPage = 1
    private val searchQuery = "covid"

    init {
        getGlobalCovidNews()
    /*    coroutineScope.launch {             //launch in IO thread
            val response = countryRepository.getUIData()  // by doing this ur class doesn't have any reference to API/Network Class
            Log.d(TAG, ": Coroutine Response: $response")

            withContext(Dispatchers.Main){
                //setValue (value) is executed in the mAIN thread
                _country.value = response // update live data
            }
        }*/

    }

    fun getGlobalCovidNews() = viewModelScope.launch {
        globalCovidNews.postValue(ResourceStatus.Loading())

        val response = newsRepository.getLatestNews(searchQuery,globalNewsPage)
        globalCovidNews.postValue(handleGlobalCovidNews(response))
    }

    private fun handleGlobalCovidNews(response: Response<NewsResponse>): ResourceStatus<NewsResponse> {
        if( response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ResourceStatus.Success(resultResponse)
            }
        }
        return ResourceStatus.Error(response.message())
    }

   /* fun getAllCountries() = viewModelScope.launch {
        globalCovidNews.postValue(ResourceStatus.Loading())

        val response = newsRepository.getLatestNews(searchQuery,globalNewsPage)
        globalCovidNews.postValue(handleGlobalCovidNews(response))
    }

    private fun handleCountryRequest(response: Response<NewsResponse>): ResourceStatus<NewsResponse> {
        if( response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ResourceStatus.Success(resultResponse)
            }
        }
        return ResourceStatus.Error(response.message())
    }*/



    override fun onCleared() {
        super.onCleared()

        coroutineScope.cancel("ViewModel cleared")
    }
}
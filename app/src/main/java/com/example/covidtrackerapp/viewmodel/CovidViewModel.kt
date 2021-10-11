package com.example.covidtrackerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidtrackerapp.model.remote.covidApi.CountryDescription
import com.example.covidtrackerapp.model.remote.covidApi.CovidResponse
import com.example.covidtrackerapp.repository.CovidCasesRepository
import com.example.covidtrackerapp.util.ResourceStatus
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response

class CovidViewModel(val covidCasesRepository: CovidCasesRepository) : ViewModel() {

    val globalCovidCases : MutableLiveData<ResourceStatus<CovidResponse>> = MutableLiveData()

    init {
        getAllCovidCases()
    }

    fun getAllCovidCases() = viewModelScope.launch {

        globalCovidCases.postValue(ResourceStatus.Loading())
        val response = covidCasesRepository.getAllCovidCases()
        globalCovidCases.postValue(handleGlobalCovidCases(response))
    }

    private fun handleGlobalCovidCases(response: Response<CovidResponse>):
            ResourceStatus<CovidResponse>? {

        if( response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ResourceStatus.Success(resultResponse)
            }
        }
        return ResourceStatus.Error(response.message())
    }


}


package com.example.covidtrackerapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covidtrackerapp.repository.CovidCasesRepository
import com.example.covidtrackerapp.viewmodel.CovidViewModel

class CovidViewModelProviderFactory(val covidCasesRepository: CovidCasesRepository) :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return CovidViewModel(covidCasesRepository) as T
    }
}
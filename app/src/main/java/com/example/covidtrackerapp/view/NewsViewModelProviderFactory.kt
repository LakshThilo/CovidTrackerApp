package com.example.covidtrackerapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covidtrackerapp.repository.NewsRepository
import com.example.covidtrackerapp.viewmodel.MainViewModel

class NewsViewModelProviderFactory(val newsRepository: NewsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        //return new instance of viewModel
        return MainViewModel(newsRepository) as T
    }
}
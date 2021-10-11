package com.example.covidtrackerapp.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.covidtrackerapp.MainActivity
import com.example.covidtrackerapp.R
import com.example.covidtrackerapp.viewmodel.MainViewModel

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel = (activity as MainActivity).viewModel
    }
}
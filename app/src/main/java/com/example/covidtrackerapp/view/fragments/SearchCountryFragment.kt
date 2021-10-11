package com.example.covidtrackerapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackerapp.MainActivity
import com.example.covidtrackerapp.R
import com.example.covidtrackerapp.util.ResourceStatus
import com.example.covidtrackerapp.view.adapters.CovidCountryDescAdapter
import com.example.covidtrackerapp.viewmodel.CovidViewModel
import kotlinx.android.synthetic.main.fragment_covid_latest_news.*
import kotlinx.android.synthetic.main.fragment_search_country.*

class SearchCountryFragment : Fragment(R.layout.fragment_search_country) {

    private  val TAG = "SearchCountryFragment"

    lateinit var covidViewModel :CovidViewModel

    lateinit var covidAdapter: CovidCountryDescAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        covidViewModel = (activity as MainActivity).covidViewModel
        setupRecyclerView()

        covidViewModel.globalCovidCases.observe(viewLifecycleOwner, Observer { response ->

            when(response) {
                is ResourceStatus.Success -> {
                    response.data?.let {
                            countryDesc ->
                        covidAdapter.differ.submitList(countryDesc.Countries)
                    }
                }

                is ResourceStatus.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "onViewCreated: $message" )
                    }
                }
                is ResourceStatus.Loading ->{
                    Log.d(TAG, "onViewCreated: Loading" )
                }
            }
        })
    }

    private fun  setupRecyclerView(){

        covidAdapter = CovidCountryDescAdapter()
        rvSearchCountry.apply {

            adapter = covidAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}


package com.example.covidtrackerapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackerapp.MainActivity
import com.example.covidtrackerapp.R
import com.example.covidtrackerapp.util.ResourceStatus
import com.example.covidtrackerapp.view.adapters.NewsAdapter
import com.example.covidtrackerapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_covid_latest_news.*

class CovidLatestNewsFragment : Fragment(R.layout.fragment_covid_latest_news) {

    val TAG = "CovidLatestNewsFragment"

    lateinit var viewModel: MainViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).mainViewModel
        setupRecyclerView()

        viewModel.globalCovidNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {

                is ResourceStatus.Success -> {
                    response.data?.let {
                        newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
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

        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {

            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
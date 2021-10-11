package com.example.covidtrackerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.covidtrackerapp.model.local.CovidRoomDatabase
import com.example.covidtrackerapp.model.local.MainRoomDatabase
import com.example.covidtrackerapp.repository.CovidCasesRepository
import com.example.covidtrackerapp.repository.NewsRepository
import com.example.covidtrackerapp.view.CovidViewModelProviderFactory
import com.example.covidtrackerapp.view.NewsViewModelProviderFactory
import com.example.covidtrackerapp.viewmodel.CovidViewModel
import com.example.covidtrackerapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

 /*   private val viewModel : MainViewModel by lazy{

        ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }*/

    lateinit var mainViewModel : MainViewModel
    lateinit var covidViewModel : CovidViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val newsRepository = NewsRepository(MainRoomDatabase(this))
        val viewModelProvider = NewsViewModelProviderFactory(newsRepository)

        val covidRepository = CovidCasesRepository(CovidRoomDatabase(this))
        val covidViewModelProvider = CovidViewModelProviderFactory(covidRepository)

        mainViewModel = ViewModelProvider(this,viewModelProvider).get(MainViewModel::class.java)
        covidViewModel = ViewModelProvider(this,covidViewModelProvider).get(CovidViewModel::class.java)

        bottomNavigationView.setupWithNavController(mainNavHostFragment.findNavController())

   /*     viewModel.country.observe(this){

            when (it) {
                is UIState.Response -> {
                    Log.d(TAG, "onCreate: $it")
                }
                is UIState.Failure -> {}
                is UIState.Loading-> {}
            }

        }*/

      /*  viewModel.globalCovidNews.observe(this) { response ->
            when(response) {

                is ResourceStatus.Success -> {
                    response.data?.let {
                        Log.e(TAG, "onViewCreated: $it" )
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
        }*/

    }

}
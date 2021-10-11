package com.example.covidtrackerapp

import android.app.Application
import com.example.covidtrackerapp.model.local.CountryDao
import com.example.covidtrackerapp.model.local.MainRoomDatabase


/** -- this call Custom Application
 *  what we doing here is create CountryDao interface, this object will create very beginning of application
 *  and this reference is going to available entire application
 *
 *  -- u need to register this in manifest
 *  */
class CountryApplication :Application()  {

    // is going to initialize variable that we need even before the main activity is created
    override fun onCreate() {
        super.onCreate()
        //countryDao = MainRoomDatabase.newInstance(this).getCountryDao()
    }

    companion object {

        lateinit var countryDao : CountryDao  // this is now static reference i don't need to pass
                                              // the context over and over again can use this refe
    }
}
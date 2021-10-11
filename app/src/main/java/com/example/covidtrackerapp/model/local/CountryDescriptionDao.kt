package com.example.covidtrackerapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidtrackerapp.model.remote.covidApi.CountryDescription

@Dao
interface CountryDescriptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(countryDescription: CountryDescription) :Long


    @Query("SELECT * FROM country_desc")
    fun getAllLatestCases(): LiveData<List<CountryDescription>>

    //SELECT SUM(Quantity) FROM OrderDetails;
 /*   @Query("SELECT SUM(TotalConfirmed) FROM country_desc")
    fun getTotalConfirmCases(): LiveData<CountryDescription>

    @Query("SELECT SUM(TotalDeaths) FROM country_desc")
    fun getTotalDeath(): LiveData<CountryDescription>*/


}
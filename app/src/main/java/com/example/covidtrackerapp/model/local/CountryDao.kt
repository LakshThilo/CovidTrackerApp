package com.example.covidtrackerapp.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidtrackerapp.model.local.entities.CountryEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface CountryDao {

    // Insert
    @Insert(entity = CountryEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: CountryEntity)


    // Select
    @Query(value = "SELECT * FROM country_details")
    fun getAllCounties(): Observable<List<CountryEntity>> // Observable going from reactivex.rxjava3
}


/**
 *  Steps to create to Room DB
 *      1. ADD dependencies
 *      2. Create the Dao Interface
 *      3. Create the Entities
 *      4. Create Room Database builder
 *      5. Create transaction in Dao
 *
 * */
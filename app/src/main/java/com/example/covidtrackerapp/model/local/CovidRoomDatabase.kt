package com.example.covidtrackerapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covidtrackerapp.model.local.entities.CountryEntity
import com.example.covidtrackerapp.model.remote.covidApi.CountryDescription


@Database(entities = [CountryDescription::class, CountryEntity::class], version = 1, exportSchema = true)
abstract class CovidRoomDatabase: RoomDatabase() {

    abstract fun getLatestCovidCases(): CountryDescriptionDao

//creating actual DB
    companion object {

        @Volatile //means that other thread can immediately see when a thread changes this instance
        private var instance : CovidRoomDatabase? = null
        private val LOCK = Any() // this is used to synchronize setting that instance that we
                                // really make sure there is only single instance of our DB

    //when ever we create a instance of out DB like "CovidRoomDatabase()"
    // call constructor on that also this invoke method
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){

        /*start synchronized block with lock object ->that means that everything that happens inside this block of code
        can't be  access by other thread at the same time */
            instance ?: createDatabase(context).also{ instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CovidRoomDatabase::class.java,
                "covid_db"
            ).build()
    }
}
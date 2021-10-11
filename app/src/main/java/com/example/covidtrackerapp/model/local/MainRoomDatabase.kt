package com.example.covidtrackerapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.covidtrackerapp.model.Article
import com.example.covidtrackerapp.model.Converters
import com.example.covidtrackerapp.model.local.entities.CountryEntity



@Database(entities = [CountryEntity::class, Article::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class MainRoomDatabase : RoomDatabase(){

    abstract fun getCountryDao() : CountryDao

    abstract fun getArticleDao(): ArticleDao




    companion object {

        @Volatile
        private var instance : MainRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){

            instance ?: createDatabase(context).also{ instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MainRoomDatabase::class.java,
                "main_db"
            ).build()
    }

   /* companion object{

        private var INSTANCE: MainRoomDatabase? = null

        fun newInstance(context: Context): MainRoomDatabase{

            var temp = INSTANCE
            if (temp != null) return temp

            synchronized(this){
                if(temp != null) return temp as MainRoomDatabase

                temp = Room.databaseBuilder(
                    context,
                    MainRoomDatabase::class.java,
                    "country_room_db"
                ).build()

                INSTANCE = temp

                return temp as MainRoomDatabase
            }
        }
    }*/
}
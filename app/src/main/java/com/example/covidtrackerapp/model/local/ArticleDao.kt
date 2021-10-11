package com.example.covidtrackerapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.covidtrackerapp.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<Article>

    @Delete
    suspend fun deleteArticle(article: Article)
}
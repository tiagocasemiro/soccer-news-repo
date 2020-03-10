package com.soccer.news.com.news.repository.remote

import com.news.domain.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleNewsApi {

    @GET("/v2/everything")
    fun articles(@Query("q") query: String, @Query("from") from: String, @Query("sortBy") sortBy: String): Call<Articles>
}
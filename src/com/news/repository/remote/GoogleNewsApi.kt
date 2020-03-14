package com.news.repository.remote

import com.news.domain.google.Articles
import com.news.domain.google.Sources
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleNewsApi {

    @GET("/v2/everything")
    fun everything(@Query("q") query: String, @Query("language") language: String = "pt"): Call<Articles>


    @GET("/v2/sources")
    fun sources(@Query("country") country: String = "br"): Call<Sources>


    @GET("/v2/top-headlines")
    fun headlines(@Query("country") country: String = "br"): Call<Articles>

    @GET("/v2/top-headlines")
    fun headlinesSource(@Query("sources") source: String): Call<Articles>

    @GET("/v2/top-headlines")
    fun category(@Query("country") country: String = "br", @Query("category") category: String): Call<Articles>
}
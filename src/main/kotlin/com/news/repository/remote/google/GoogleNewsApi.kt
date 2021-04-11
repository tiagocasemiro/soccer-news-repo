package com.news.repository.remote.google

import com.news.domain.Articles
import com.news.domain.Sources
import countryDefault
import languageDefault
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleNewsApi {

    @GET("/v2/everything")
    fun everything(@Query("q") query: String, @Query("language") language: String = languageDefault): Call<Articles>

    @GET("/v2/sources")
    fun sources(@Query("country") country: String = countryDefault): Call<Sources>

    @GET("/v2/top-headlines")
    fun headlines(@Query("country") country: String = countryDefault): Call<Articles>

    @GET("/v2/top-headlines")
    fun headlinesSource(@Query("sources") source: String): Call<Articles>

    @GET("/v2/top-headlines")
    fun category(@Query("country") country: String = countryDefault, @Query("category") category: String): Call<Articles>
}
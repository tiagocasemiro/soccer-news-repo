package com.news.domain

import com.google.gson.annotations.SerializedName

data class Article (
    @SerializedName("source") val source : Source? = null,
    @SerializedName("author") val author : String? = null,
    @SerializedName("title") val title : String? = null,
    @SerializedName("description") val description : String? = null,
    @SerializedName("url") val url : String? = null,
    @SerializedName("urlToImage") val urlToImage : String? = null,
    @SerializedName("publishedAt") val publishedAt : String? = null,
    @SerializedName("content") val content : String? = null
)

data class Articles (
    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults : Int,
    @SerializedName("articles") val articles : List<Article>
)
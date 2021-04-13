package com.news.domain

import com.google.gson.annotations.SerializedName

data class Category(@SerializedName("name") val name: String)

data class Categories(
    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults : Int,
    @SerializedName("categories") val categories: List<Category>
)
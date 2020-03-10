package com.news.domain

import com.google.gson.annotations.SerializedName

data class Articles (
	@SerializedName("status") val status : String,
	@SerializedName("totalResults") val totalResults : Int,
	@SerializedName("articles") val articles : List<Article>
)
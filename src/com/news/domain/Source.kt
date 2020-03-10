package com.news.domain

import com.google.gson.annotations.SerializedName

data class Source (

	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String
)
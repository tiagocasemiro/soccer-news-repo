package com.news.domain.google

import com.google.gson.annotations.SerializedName

data class Sources (
	@SerializedName("status") val status : String,
	@SerializedName("sources") val sources : List<Source>
)
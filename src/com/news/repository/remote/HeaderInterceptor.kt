package com.soccer.news.com.news.repository.remote

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    private val apiKey = "71e188afa11b45f9998587517c6a6b93"

    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url.newBuilder().addQueryParameter("apiKey", apiKey).build()
        val request: Request = chain.request().newBuilder().url(url).build()

        return chain.proceed(request)
    }
}
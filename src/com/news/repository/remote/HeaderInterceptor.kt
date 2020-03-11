package com.soccer.news.com.news.repository.remote

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    private val apiKey = "71e188afa11b45f9998587517c6a6b93"
    private val sourceBr = "google-news-br"

    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url.newBuilder()
            .addQueryParameter("sources", sourceBr)
            .build()

        val builder = chain.request().newBuilder().url(url)
            .addHeader("X-Api-Key", apiKey)

        val request: Request = builder.build()

        return chain.proceed(request)
    }
}
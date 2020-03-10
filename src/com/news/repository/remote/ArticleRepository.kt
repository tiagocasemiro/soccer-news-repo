package com.soccer.news.com.news.repository.remote

import com.news.domain.Articles
import java.net.UnknownHostException

class ArticleRepository(private val googleNewsApi: GoogleNewsApi) {

    fun articles(query: String, from: String, sortBy: String): Articles {
        val response = googleNewsApi.articles(query, from, sortBy).execute()

        response.body()?.let { activation ->
            return activation
        }
        throw UnknownHostException()
    }
}
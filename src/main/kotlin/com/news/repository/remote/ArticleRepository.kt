package com.news.repository.remote

import com.news.domain.google.Articles
import com.news.domain.google.Sources
import java.net.UnknownHostException

class ArticleRepository(private val googleNewsApi: GoogleNewsApi) {

    fun everything(query: String): Articles {
        val response = googleNewsApi.everything(query).execute()

        response.body()?.let { activation ->
            return activation
        }
        throw UnknownHostException()
    }

    fun headlines(): Articles {
        return googleNewsApi.headlines().execute().body()?: throw UnknownHostException()
    }

    fun headlinesSource(source: String): Articles {
        return googleNewsApi.headlinesSource(source).execute().body()?: throw UnknownHostException()
    }

    fun category(category: String): Articles {
        return googleNewsApi.category(category = category).execute().body()?: throw UnknownHostException()
    }

    fun sources(): Sources {
        return googleNewsApi.sources().execute().body()?: throw UnknownHostException()
    }
}
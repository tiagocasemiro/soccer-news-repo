package com.news.service

import com.news.domain.google.Articles
import com.news.domain.google.Sources
import com.news.repository.remote.ArticleRepository

class ArticleService(private val articleRepository: ArticleRepository) {

    fun everything(query: String): Articles {
        return articleRepository.everything(query)
    }

    fun headlines(): Articles {
        return articleRepository.headlines()
    }

    fun headlines(source: String): Articles {
        return articleRepository.headlinesSource(source)
    }

    fun category(category: String): Articles {
        return articleRepository.category(category)
    }

    fun sources(): Sources {
        return articleRepository.sources()
    }
}
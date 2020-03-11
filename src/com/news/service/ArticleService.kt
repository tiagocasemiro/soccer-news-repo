package com.soccer.news.com.news.service

import com.news.domain.Articles
import com.soccer.news.com.news.repository.remote.ArticleRepository

class ArticleService(private val articleRepository: ArticleRepository) {

    fun articles(query: String, from: String, sortBy: String): Articles {
        return articleRepository.articles(query, from, sortBy)
    }
}
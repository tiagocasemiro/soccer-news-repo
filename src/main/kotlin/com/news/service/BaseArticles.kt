package com.news.service

import com.news.domain.google.Article
import com.news.domain.google.Articles

interface BaseArticles<T> {
    val from: T
    fun article(position: Int): Article
    fun articles(): Articles
    fun count(): Int
}
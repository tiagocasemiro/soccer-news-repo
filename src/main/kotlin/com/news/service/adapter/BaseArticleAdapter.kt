package com.news.service.adapter

import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

abstract class BaseArticleAdapter<T>(from: SyndFeed) {
    private val entries = from.entries?: listOf<SyndEntry>()
    private val statusSuccess = "ok"

    fun count(): Int {
        return entries.size
    }

    fun articles(): List<T> {
        val listArticle = mutableListOf<T>()
        entries.forEach { itemRss ->
            listArticle.add(article(itemRss))
        }

        return listArticle
    }

    abstract fun article(entry: SyndEntry): T
}
package com.news.service

import com.news.domain.google.Article
import com.news.domain.google.Articles
import com.news.domain.google.Source
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

open class BaseArticleAdapter(private val from: SyndFeed) {
    private val entries = from.entries?: listOf<SyndEntry>()
    private val statusSuccess = "ok"

    fun count(): Int {
        return entries.size
    }

    fun articles(): Articles {
        val listArticle = mutableListOf<Article>()
        entries.forEach {
            listArticle.add(article(it))
        }

        return Articles(statusSuccess, count(), listArticle)
    }

    open fun article(entry: SyndEntry): Article {
        return Article(
            source = Source(name = from.author?: from.title, description = from.description, url = from.link),
            author = entry.author,
            title = entry.title,
            description = entry.description.value,
            url = entry.link,
            publishedAt = entry.publishedDate?.time?.toString(),
            content = entry.description.value
        )
    }
}
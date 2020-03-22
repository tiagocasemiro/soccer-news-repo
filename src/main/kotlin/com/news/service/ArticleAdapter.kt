package com.news.service

import com.news.domain.google.Article
import com.news.domain.google.Articles
import com.news.domain.google.Source
import com.rometools.rome.feed.synd.SyndFeed

class ArticleAdapter(override val from: SyndFeed): BaseArticles<SyndFeed> {
    private val entries = from.entries
    private val statusSuccess = "ok"

    override fun count(): Int {
        return entries.size
    }

    override fun article(position: Int): Article {
        val entry = entries[position]

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

    override fun articles(): Articles {
        val listArticle = mutableListOf<Article>()
        entries.forEachIndexed { index, _ ->
            listArticle.add(article(index))
        }

        return Articles(statusSuccess, count(), listArticle)
    }
}
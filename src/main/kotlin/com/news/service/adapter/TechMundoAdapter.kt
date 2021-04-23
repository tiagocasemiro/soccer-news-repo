package com.news.service.adapter

import com.news.domain.Article
import com.news.domain.Source
import com.news.domain.techMundoSource
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

class TechMundoAdapter(from: SyndFeed) : BaseArticleAdapter<Article>(from) {

    override fun article(entry: SyndEntry) = Article(
        source = techMundoSource(),
        author = entry.author,
        title = entry.title,
        description  = entry.description.value,
        url  = entry.uri,
        urlToImage  = entry.enclosures[0].url,
        publishedAt = entry.publishedDate.toString(),
        content = entry.description.value
    )
}
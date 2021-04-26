package com.news.service.adapter

import com.news.domain.Article
import com.news.domain.theInterceptBrazilSource
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

class MicrosoftAdapter(from: SyndFeed) : BaseArticleAdapter<Article>(from) {
    override fun article(entry: SyndEntry) = Article(
        source = theInterceptBrazilSource(),
        author = entry.author,
        title = entry.title,
        description  = entry.description.value,
        url  = entry.uri,
        urlToImage  = entry.foreignMarkup?.let { if(it.size > 2) it[2].getAttribute("url")?.value else null},
        publishedAt = entry.publishedDate.toString(),
        content = entry.contents[0].value
    )
}
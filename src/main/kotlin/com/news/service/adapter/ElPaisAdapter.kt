package com.news.service.adapter

import com.news.domain.Article
import com.news.domain.elPaisSource
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

class ElPaisAdapter(from: SyndFeed) : BaseArticleAdapter<Article>(from) {

    override fun article(entry: SyndEntry) = Article(
        source = elPaisSource(),
        author = entry.author,
        title = entry.title,
        description  = entry.description.value,
        url  = entry.link,
        urlToImage  = entry.foreignMarkup.takeIf { it.size > 1 }?.get(1)?.getAttributeValue("url"),
        publishedAt = entry.publishedDate.toString(),
        content = entry.contents?.toString()
    )
}

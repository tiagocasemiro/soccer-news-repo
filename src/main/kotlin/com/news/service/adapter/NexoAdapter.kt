package com.news.service.adapter

import com.news.domain.nexo.NexoArticle
import com.news.domain.nexo.Source
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

class NexoAdapter(from: SyndFeed) : BaseArticleAdapter<NexoArticle>(from) {

    override fun article(entry: SyndEntry): NexoArticle {

        return NexoArticle(
            source = Source(
                name = entry.source?.title?: "Nexo Jornal",
                description = entry.description.value,
                link = entry.link,
                image = null),
            title = entry.title,
            link = entry.link,
            pubdate = "",
            description = entry.description.value)
    }
}
package com.news.service.adapter

import com.news.domain.Article
import com.news.domain.Source
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

class TechMundoAdapter(from: SyndFeed) : BaseArticleAdapter<Article>(from) {
    override fun article(entry: SyndEntry): Article {
        return Article(
            source = Source(
                name = entry.source?.title?:"Novidades do TecMundo",
                description = entry.source?.description?:"Descubra e aprenda tudo sobre tecnologia",
                url = entry.source?.link?: "http://www.tecmundo.com.br/",
                category = entry.source?.categories?.toString()?: "Tecnologia",
                language = entry.source?.language?: "Português"),
            author = entry.author,
            title = entry.title,
            description  = entry.description.value,
            url  = entry.uri,
            urlToImage  = entry.enclosures[0].url,
            publishedAt = entry.publishedDate.toString(),
            content = entry.description.value
        )
    }
}
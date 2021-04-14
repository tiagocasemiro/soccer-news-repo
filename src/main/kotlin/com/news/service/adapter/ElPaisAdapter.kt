package com.news.service.adapter

import com.news.domain.Article
import com.news.domain.Source
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

class ElPaisAdapter(from: SyndFeed) : BaseArticleAdapter<Article>(from) {

    override fun article(entry: SyndEntry): Article {
        return Article(
            source = Source(
                name = entry.source?.title?:"Edição Brasil no EL PAÍS: o jornal global",
                description = entry.source?.description?:"Últimas notícias do Brasil e do mundo: política, economia, esportes, cultura e sociedade no EL PAÍS. Além disso, especiais, vídeos, fotos, áudios, gráficos e entrevistas com o EL PAÍS.",
                url = entry.source?.link?: "https://brasil.elpais.com",
                category = entry.source?.categories?.toString()?: "General",
                language = entry.source?.language?: "Português",
                country = "Brasil"),
            author = entry.author,
            title = entry.title,
            description  = entry.description.value,
            url  = entry.link,
            urlToImage  = entry.foreignMarkup.takeIf { it.size > 1 }?.get(1)?.getAttributeValue("url"),
            publishedAt = entry.publishedDate.toString(),
            content = entry.contents?.toString()
        )
    }
}

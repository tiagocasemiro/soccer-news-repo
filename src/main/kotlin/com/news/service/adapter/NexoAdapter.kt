package com.news.service.adapter

import com.google.gson.annotations.SerializedName
import com.news.domain.Article
import com.news.domain.Source
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed
class NexoAdapter(from: SyndFeed) : BaseArticleAdapter<Article>(from) {

    override fun article(entry: SyndEntry): Article {

        return Article(
            source = Source(
                id = "nexo-jornal",
                name = entry.source?.title?: "Nexo Jornal",
                description = entry.source?.description,
                url = entry.source?.link,
                language = entry.source?.language?: "Português",
                country = "Brasil",
                category = "general"),
            title = entry.title,
            url = entry.link,
            publishedAt = entry.publishedDate?.toString(),
            description = entry.description?.value,
            author = entry.author,
            urlToImage = extractImageFromDescription(entry.description?.toString()),
            content = extractContentFromDescription(entry.description?.toString()))
    }

    /*

    entry.description

    <img src=https://www.nexojornal.com.br/incoming/imagens/bolsonaristas.JPG/alternates/BASE_LANDSCAPE_SMALL/bolsonaristas.JPG/>
    <p>Da realização de celebrações religiosas às contestações à vacinação, escolhas individuais têm grande impacto social</p>

    */
    private fun extractImageFromDescription(description: String?): String {
        // todo implement function

        return ""
    }

    private fun extractContentFromDescription(description: String?): String {
        // TODO implement function

        return ""
    }
}


@SerializedName("content") val content : String? = null
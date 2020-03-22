package com.news.service

import com.news.domain.google.Article
import com.news.domain.google.Articles
import com.news.domain.google.Sources
import com.news.repository.remote.exame.ExameRepository
import com.news.repository.remote.google.ArticleRepository
import com.news.repository.remote.nexo.NexoRepository
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.sun.org.apache.bcel.internal.generic.RETURN
import java.net.URL

class ArticleService(private val articleRepository: ArticleRepository, private  val exameRepository: ExameRepository, private val nexoRepository: NexoRepository) {

    fun everything(query: String): Articles {
        return articleRepository.everything(query)
    }

    fun headlines(): Articles {
        return articleRepository.headlines()
    }

    fun headlines(source: String): Articles {
        return articleRepository.headlinesSource(source)
    }

    fun category(category: String): Articles {
        return articleRepository.category(category)
    }

    fun sources(): Sources {
        return articleRepository.sources()
    }

    fun exame(): Articles {
        val exame = exameRepository.feed()
        val adapter = ArticleAdapter(exame)

        return adapter.articles()
    }

    fun nexo(): Articles {
        val nexo = nexoRepository.feed()
        val adapter = ArticleAdapter(nexo)

        return adapter.articles()
    }

}
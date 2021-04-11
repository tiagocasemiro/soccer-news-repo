package com.news.service

import com.news.domain.google.Articles
import com.news.domain.google.Sources
import com.news.domain.nexo.NexoArticle
import com.news.repository.remote.google.ArticleRepository
import com.news.repository.remote.intercept.InterceptRepository
import com.news.repository.remote.nexo.NexoRepository
import com.news.repository.remote.techmundo.TechMundoRepository
import com.news.service.adapter.InterceptAdapter
import com.news.service.adapter.NexoAdapter
import com.news.service.adapter.TechMundoAdapter

class ArticleService(
    private val articleRepository: ArticleRepository,
    private val nexoRepository: NexoRepository,
    private val interceptRepository: InterceptRepository,
    private val techMundoRepository: TechMundoRepository) {

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

    fun nexo(): List<NexoArticle> {
        val nexo = nexoRepository.feed()
        val adapter = NexoAdapter(nexo)

        return adapter.articles()
    }

    fun theInterceptBrazil(): Articles {
        val intercept = interceptRepository.feed()
        val adapter = InterceptAdapter(intercept)
        val list = adapter.articles()

        return Articles(status = "success", totalResults = list.size, articles = list)
    }

    fun techMundo(): Articles {
        val techMundo = techMundoRepository.feed()
        val adapter = TechMundoAdapter(techMundo)
        val list = adapter.articles()

        return Articles(status = "success", totalResults = list.size, articles = list)
    }

}
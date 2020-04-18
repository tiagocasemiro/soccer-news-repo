package com.news.service

import com.news.domain.google.Articles
import com.news.domain.google.Sources
import com.news.repository.remote.exame.ExameRepository
import com.news.repository.remote.google.ArticleRepository
import com.news.repository.remote.intercept.InterceptRepository
import com.news.repository.remote.nexo.NexoRepository
import com.news.repository.remote.techmundo.TechMundoRepository

class ArticleService(
    private val articleRepository: ArticleRepository,
    private val exameRepository: ExameRepository,
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

    fun exame(): Articles {
        val exame = exameRepository.feed()
        val adapter = BaseArticleAdapter(exame)

        return adapter.articles()
    }

    fun nexo(): Articles {
        val nexo = nexoRepository.feed()
        val adapter = BaseArticleAdapter(nexo)

        return adapter.articles()
    }

    fun intercept(): Articles {
        val nexo = interceptRepository.feed()
        val adapter = BaseArticleAdapter(nexo)

        return adapter.articles()
    }

    fun techmundo(): Articles {
        val nexo = techMundoRepository.feed()
        val adapter = BaseArticleAdapter(nexo)

        return adapter.articles()
    }

}
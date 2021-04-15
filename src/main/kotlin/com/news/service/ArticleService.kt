package com.news.service

import com.news.domain.*
import com.news.repository.remote.elpais.ElPaisRepository
import com.news.repository.remote.google.ArticleRepository
import com.news.repository.remote.intercept.InterceptRepository
import com.news.repository.remote.nexo.NexoRepository
import com.news.repository.remote.techmundo.TechMundoRepository
import com.news.service.adapter.ElPaisAdapter
import com.news.service.adapter.InterceptAdapter
import com.news.service.adapter.NexoAdapter
import com.news.service.adapter.TechMundoAdapter

class ArticleService(
    private val articleRepository: ArticleRepository,
    private val nexoRepository: NexoRepository,
    private val interceptRepository: InterceptRepository,
    private val techMundoRepository: TechMundoRepository,
    private val elPaisRepository: ElPaisRepository) {

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

    fun categories(): Categories {
        val list = listOf("business", "entertainment", "general", "health", "science", "sports", "technology").map { Category(it) }
        return Categories(
            status = "SUCCESS",
            categories = list,
            totalResults = list.size
        )
    }

    fun sources(): Sources {
        val sources = articleRepository.sources()
        sources.apply {
            totalResults = this.sources.size
        }

        return sources
    }

    fun nexo(): Articles {
        val nexo = nexoRepository.feed()
        val adapter = NexoAdapter(nexo)
        val list = adapter.articles()

        return Articles(status = "success", totalResults = list.size, articles = list)
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

    fun elPais(): Articles {
        val elPais = elPaisRepository.feed()
        val adapter = ElPaisAdapter(elPais)
        val list = adapter.articles()

        return Articles(status = "success", totalResults = list.size, articles = list)
    }
}

//todo *** adicionar nova fonte ***
//todo https://feeds.elpais.com/mrss-s/pages/ep/site/brasil.elpais.com/portada
//todo https://www.microsoft.com/en-us/edge/microsoft-news
//todo https://medium.com/rakuten-rapidapi/top-10-best-news-apis-google-news-bloomberg-bing-news-and-more-bbf3e6e46af6
//todo https://www.bloomberg.com.br/agende-uma-demo/
//todo https://newsapi.ai/
//todo https://webhose.io/news-api/brazil-news-api
//todo https://newsapi.org/s/brazil-news-api
//todo https://www.scaleserp.com/docs/locations-api/overview
//todo https://webhose.io/news-api/brazil-news-api
//todo https://mediastack.com/sources/brazil-news-api
//todo https://www.serpwow.com/google-news-api?gclid=Cj0KCQjwpdqDBhCSARIsAEUJ0hPbiaaeP_BdSKgakjBmNvL-RwLPUi6jxDqYn_do7z1neL-yHpbnp-gaAl7bEALw_wcB
//todo




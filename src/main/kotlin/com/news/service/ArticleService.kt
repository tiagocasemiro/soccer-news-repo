package com.news.service

import com.news.repository.remote.techmundo.TechMundoRepository
import com.news.repository.remote.intercept.InterceptRepository
import com.news.repository.remote.google.ArticleRepository
import com.news.repository.remote.elpais.ElPaisRepository
import com.news.repository.remote.nexo.NexoRepository
import com.news.domain.*
import com.news.repository.remote.microsoft.MicrosoftRepository
import com.news.service.adapter.*

class ArticleService(
    private val articleRepository: ArticleRepository,
    private val nexoRepository: NexoRepository,
    private val interceptRepository: InterceptRepository,
    private val techMundoRepository: TechMundoRepository,
    private val elPaisRepository: ElPaisRepository,
    private val microsoftRepository: MicrosoftRepository) {

    fun everything(query: String): Articles {
        return articleRepository.everything(query)
    }

    fun headlines(): Articles {
        return articleRepository.headlines()
    }

    fun headlines(source: String): Articles {
        return when(source) {
            nexoSource().id -> nexo()
            techMundoSource().id -> techMundo()
            theInterceptBrazilSource().id -> theInterceptBrazil()
            elPaisSource().id -> elPais()
            else -> articleRepository.headlinesSource(source)
        }
    }

    fun category(category: String): Articles {
        return articleRepository.category(category)
    }

    fun categories(): Categories {
        val list = listOf(Category("business", "Negócios"), Category("entertainment", "Entretenimento"), Category("general", "Geral"), Category("health", "Principal"), Category("science", "Ciência"), Category("sports", "Esportes"), Category("technology", "Tecnologia"), Category("politics", "Política"))
        return Categories(
            status = "ok",
            categories = list,
            totalResults = list.size
        )
    }

    fun sources(): Sources {
        val sources = articleRepository.sources()
        val allSource = mutableListOf<Source>()
        allSource.addAll(sources.sources)
        allSource.addAll(extraSources())

        return Sources (
            status = "ok",
            sources = allSource,
            totalResults = allSource.size
        )
    }

    fun nexo(): Articles {
        val nexo = nexoRepository.feed()
        val adapter = NexoAdapter(nexo)
        val list = adapter.articles()

        return Articles(status = "ok", totalResults = list.size, articles = list)
    }

    fun theInterceptBrazil(): Articles {
        val intercept = interceptRepository.feed()
        val adapter = InterceptAdapter(intercept)
        val list = adapter.articles()

        return Articles(status = "ok", totalResults = list.size, articles = list)
    }

    fun techMundo(): Articles {
        val techMundo = techMundoRepository.feed()
        val adapter = TechMundoAdapter(techMundo)
        val list = adapter.articles()

        return Articles(status = "ok", totalResults = list.size, articles = list)
    }

    fun elPais(): Articles {
        val elPais = elPaisRepository.feed()
        val adapter = ElPaisAdapter(elPais)
        val list = adapter.articles()

        return Articles(status = "ok", totalResults = list.size, articles = list)
    }

    fun microsoft(): Articles { // todo add this to headlines
        val microsoft = microsoftRepository.feed()
        val adapter = MicrosoftAdapter(microsoft)
        val list = adapter.articles()

        return Articles(status = "ok", totalResults = list.size, articles = list)
    }
}

//todo *** adicionar nova fonte ***

//todo Integrar - Conta feita com gmail (Verificar melhor)
//todo https://rapidapi.com/microsoft-azure-org-microsoft-cognitive-services/api/bing-news-search1/pricing

// todo Integrar
//todo https://newsapi.ai/


// todo Integrar
//todo https://mediastack.com/sources/brazil-news-api




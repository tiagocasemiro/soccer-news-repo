package com.repository.remote

import com.news.domain.google.Articles
import com.news.service.ArticleService
import configuration
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import kotlin.test.assertEquals

class ArticleRepositoryTest {
    private val articleService: ArticleService = mockk()

    private val serviceModulesMock = module(override = true) {
        factory {
            articleService
        }
    }

    @Test
    fun `deve retornar o status up quando raiz for chamado com metodo get`() {
        withTestApplication(Application::configuration) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("{\"status\":\"UP\"}", response.content)
            }
        }
    }

    @Test
    fun `deve retornar as principais noticias quando service responder com sucesso`() {
        every { articleService.headlines() } returns Articles("ok", 0, listOf())

        withTestApplication(Application::configuration) {
            loadKoinModules(listOf(serviceModulesMock))

            handleRequest(HttpMethod.Get, "/headlines").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("{\"status\":\"ok\",\"totalResults\":0,\"articles\":[]}", response.content)
            }
        }
    }
}
package com.news.routes

import com.news.service.ArticleService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject
import java.net.UnknownHostException

fun Routing.articles() {
    val articleService: ArticleService by inject()

    get("/") {
        call.respond(mapOf("status" to "UP"))
    }

    get("/headlines") {
        try {
            call.respond(articleService.headlines())
        }catch (e: UnknownHostException) {
            call.response.status(HttpStatusCode.FailedDependency)
        }catch (e: Exception) {
            call.response.status(HttpStatusCode.InternalServerError)
        }
    }

    get("/everything") {
        try {
            call.parameters["query"]?.let { q ->
                call.respond(articleService.everything(q))
            }?: run {
                call.respond(articleService.headlines())
            }
        }catch (e: UnknownHostException) {
            call.response.status(HttpStatusCode.FailedDependency)
        }catch (e: Exception) {
            call.response.status(HttpStatusCode.InternalServerError)
        }
    }

    get("/headlines/source/{source}") {
        try {
            val source = call.parameters["source"]!!
            call.respond(articleService.headlines(source))
        }catch (e: UnknownHostException) {
            call.response.status(HttpStatusCode.FailedDependency)
        }catch (e: Exception) {
            call.response.status(HttpStatusCode.InternalServerError)
        }
    }

    get("/sources") {
        try {
            call.respond(articleService.sources())
        }catch (e: UnknownHostException) {
            call.response.status(HttpStatusCode.FailedDependency)
        }catch (e: Exception) {
            call.response.status(HttpStatusCode.InternalServerError)
        }
    }

    get("/category/{category}") {
        try {
            val category: String = call.parameters["category"]!!
            call.respond(articleService.category(category))
        }catch (e: UnknownHostException) {
            call.response.status(HttpStatusCode.FailedDependency)
        }catch (e: Exception) {
            call.response.status(HttpStatusCode.InternalServerError)
        }
    }

    get("/exame") {
        try {
            call.respond(articleService.exame())
        }catch (e: UnknownHostException) {
            call.response.status(HttpStatusCode.FailedDependency)
        }catch (e: Exception) {
            call.response.status(HttpStatusCode.InternalServerError)
        }
    }

    get("/nexo") {
        try {
            call.respond(articleService.nexo())
        }catch (e: UnknownHostException) {
            call.response.status(HttpStatusCode.FailedDependency)
        }catch (e: Exception) {
            call.response.status(HttpStatusCode.InternalServerError)
        }
    }
}
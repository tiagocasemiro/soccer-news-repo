@file:Suppress("unused")

package com.news.routes

import com.news.domain.google.Articles
import com.news.domain.google.Sources
import com.news.service.ArticleService
import com.papsign.ktor.openapigen.annotations.parameters.PathParam
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam
import com.papsign.ktor.openapigen.openAPIGen
import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.FailedDependency
import io.ktor.response.*
import io.ktor.routing.*
import java.net.UnknownHostException
import kotlin.reflect.full.createInstance

open class NoParameter

fun Routing.documentations() {

    get("/openapi.json") {
        call.respond(application.openAPIGen.api.serialize())
    }
    get("/api") {
        call.respondRedirect("/swagger-ui/index.html?url=/openapi.json", true)
    }
}

fun NormalOpenAPIRoute.articles(articleService: ArticleService) {

    route("/").get<NoParameter, Map<String, String>>(example = mapOf("status" to "UP")) {
        respond(emptyMap())
    }
    data class EverythingParameters(@QueryParam("A query to find articles") val query: String? = null)
    data class HeadlinesSourceParameters(@PathParam("A source to filter articles") val source: String)
    data class CategoryParameters(@PathParam("A category to find articles") val category: String)

    throws(InternalServerError,Exception::class) {
        throws(FailedDependency, UnknownHostException::class) {
            route("/headlines").get<NoParameter, Articles>(info("All articles available", "List all articles")) {
                respond(articleService.headlines())
            }

            route("/everything").get<EverythingParameters, Articles> { parameters ->
                parameters.query?.let { q ->
                    respond(articleService.everything(q))
                }?: run {
                    respond(articleService.headlines())
                }
            }

            route("/headlines/source/{source}").get<HeadlinesSourceParameters, Articles> { parameters ->
                respond(articleService.headlines(parameters.source))
            }

            route("/sources").get<NoParameter, Sources>(info("Sources available", "List all source")) {
                respond(articleService.sources())
            }

            route("/category/{category}").get<CategoryParameters, Articles>(info("Articles available on category", "List all articles of category")) { parameters ->
                 respond(articleService.category(parameters.category))
            }
        }
    }
}
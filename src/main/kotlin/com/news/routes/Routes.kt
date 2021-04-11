@file:Suppress("unused")

package com.news.routes

import com.news.domain.Article
import com.news.domain.Articles
import com.news.domain.Sources
import com.news.service.ArticleService
import com.papsign.ktor.openapigen.annotations.parameters.PathParam
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam
import com.papsign.ktor.openapigen.openAPIGen
import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import com.papsign.ktor.openapigen.route.throws
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.FailedDependency
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.response.*
import io.ktor.routing.*
import java.net.UnknownHostException

open class NoParameter

fun Routing.documentations() {

    get("/openapi.json") {
        call.respond(application.openAPIGen.api.serialize())
    }
    get("/swagger-ui") {
        call.respondRedirect("/swagger-ui/index.html?url=/openapi.json", true)
    }
}

fun NormalOpenAPIRoute.articles(articleService: ArticleService) {
    data class EverythingParameters(@QueryParam("A query to find articles") val query: String? = null)
    data class HeadlinesSourceParameters(@PathParam("A source to filter articles") val source: String)
    data class CategoryParameters(@PathParam("A category to find articles") val category: String)

    route("/health").get<NoParameter, Map<String, String>>(info("Status server", "Return UP if server working")) {
        respond(mapOf("status" to "UP"))
    }
    throws(InternalServerError, Exception::class) {
        throws(FailedDependency, UnknownHostException::class) {
            route("/headlines").get<NoParameter, Articles>(info("All main articles ", "List all main articles in the moment")) {
                respond(articleService.headlines())
            }

            route("/everything").get<EverythingParameters, Articles>(info("All articles available", "List all articles")) { parameters ->
                parameters.query?.let { q ->
                    respond(articleService.everything(q))
                }?: run {
                    respond(articleService.headlines())
                }
            }

            route("/headlines/source/{source}").get<HeadlinesSourceParameters, Articles>(info("All main articles of source", "List all main articles by source on the moment")) { parameters ->
                respond(articleService.headlines(parameters.source))
            }

            route("/sources").get<NoParameter, Sources>(info("Sources available", "List all source")) {
                respond(articleService.sources())
            }

            route("/categories").get<NoParameter, List<String>>(info("Categories available", "List all categories")) {
                respond(articleService.categories())
            }

            route("/category/{category}").get<CategoryParameters, Articles>(info("Articles available on category", "List all articles of category")) { parameters ->
                 respond(articleService.category(parameters.category))
            }

            route("/nexo").get<NoParameter, List<Article>>(info("Articles available from nexo", "List all articles of nexo")) {
                respond(articleService.nexo())
            }

            route("/the-intercept-brasil").get<NoParameter, Articles>(info("Articles available from the intercept brasil", "List all articles of the intercept brasil")) {
                respond(articleService.theInterceptBrazil())
            }

            route("/tech-mundo").get<NoParameter, Articles>(info("Articles available from tech mundo", "List all articles of tech mundo")) {
                respond(articleService.techMundo())
            }
        }
    }
}
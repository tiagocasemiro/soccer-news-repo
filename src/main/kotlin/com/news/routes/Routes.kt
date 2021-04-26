@file:Suppress("unused")

package com.news.routes

import com.news.service.ArticleService
import com.news.domain.Categories
import com.news.domain.Articles
import com.news.domain.Sources
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam
import com.papsign.ktor.openapigen.annotations.parameters.PathParam
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.throws
import com.papsign.ktor.openapigen.route.route
import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.openAPIGen
import java.net.UnknownHostException
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.FailedDependency
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

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

            route("/categories").get<NoParameter, Categories>(info("Categories available", "List all categories")) {
                respond(articleService.categories())
            }

            route("/category/{category}").get<CategoryParameters, Articles>(info("Articles available on category", "List all articles of category")) { parameters ->
                 respond(articleService.category(parameters.category))
            }

            route("/nexo").get<NoParameter, Articles>(info("Articles available from nexo", "List all articles of nexo")) {
                respond(articleService.nexo())
            }

            route("/el-pais").get<NoParameter, Articles>(info("Articles available from el pais", "List all articles of el pais")) {
                respond(articleService.elPais())
            }

            route("/the-intercept-brazil").get<NoParameter, Articles>(info("Articles available from the intercept brasil", "List all articles of the intercept brasil")) {
                respond(articleService.theInterceptBrazil())
            }

            route("/tech-mundo").get<NoParameter, Articles>(info("Articles available from tech mundo", "List all articles of tech mundo")) {
                respond(articleService.techMundo())
            }

            route("/tech-mundo").get<NoParameter, Articles>(info("Articles available from microsoft news", "List all articles of microsoft")) {
                respond(articleService.microsoft())
            }
        }
    }
}
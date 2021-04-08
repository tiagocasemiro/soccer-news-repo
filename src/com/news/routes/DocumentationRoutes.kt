@file:Suppress("unused")

package com.news.routes

import com.news.domain.google.Articles
import com.news.domain.google.Sources
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
import io.ktor.http.*
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

fun NormalOpenAPIRoute.documentations() {
    route("/").get<NoParameter, Map<String, String>>(example = mapOf("status" to "UP")) {
        respond(emptyMap())
    }

    throws(HttpStatusCode.FailedDependency, UnknownHostException::class) {
        throws(HttpStatusCode.InternalServerError, Exception::class) {
            route("/headlines").get<NoParameter, Articles>(info("All articles available", "List all articles")) {
                respond(Articles::class.createInstance())
            }

            class EverythingParameter(@QueryParam("A query to find articles") val query: String? = null)
            route("/everything").get<EverythingParameter, Articles> {
                respond(Articles::class.createInstance())
            }

            class HeadlinesSourceParameter(@PathParam("A source to filter articles") val source: String)
            route("/headlines/source/{source}").get<HeadlinesSourceParameter, Articles> {
                respond(Articles::class.createInstance())
            }

            route("/sources").get<NoParameter, Sources>(info("Sources available", "List all source")) {
                respond(Sources::class.createInstance())
            }

            class CategoryParameter(@PathParam("A category to find articles") val category: String)
            route("/category/{category}").get<CategoryParameter, Articles>(info("Articles available on category", "List all articles of category")) {
                respond(Articles::class.createInstance())
            }
        }
    }
}
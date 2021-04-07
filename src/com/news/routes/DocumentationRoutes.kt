package com.news.routes

import com.papsign.ktor.openapigen.openAPIGen
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.documentations() {
    get("/openapi.json") {
        call.respond(application.openAPIGen.api.serialize())
    }
    get("/api") {
        call.respondRedirect("/swagger-ui/index.html?url=/openapi.json", true)
    }
}
package com.soccer.news

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.ktor.ext.Koin


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }
    install(Koin) {
        modules(
            listOf(
                org.koin.dsl.module {

                }
            )
        )
    }
    routing {
        get("/health") {
            call.respond(mapOf("status" to "UP"))
        }
        get("/") {
            call.respond(mapOf("status" to "UP"))
        }
    }
}


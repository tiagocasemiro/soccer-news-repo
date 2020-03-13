import com.soccer.news.com.news.service.ArticleService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import java.net.UnknownHostException


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(ContentNegotiation) {
        gson {
        }
    }
    install(Koin) {
        modules(
           listOf(
               retrofitModules,
               sericeModules,
               repositoryModules
           )
        )
    }

    routing {
        val articleService: ArticleService by inject()

        get("/") {
            call.respond(mapOf("status" to "UP"))
        }
        get("/health") {
            call.respond(mapOf("status" to "UP"))
        }
        get("/articles") {
            try {
                val query = "bitcoin"
                val from = "2020-02-13"
                val sortBy = "publishedAt"

                val articles = articleService.articles(query, from, sortBy)
                call.respond(articles)
            }catch (e: UnknownHostException) {
                call.response.status(HttpStatusCode.FailedDependency)
            }catch (e: Exception) {
                call.response.status(HttpStatusCode.InternalServerError)
            }
        }
    }
}


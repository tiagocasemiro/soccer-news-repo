import com.news.routes.articles
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import org.koin.ktor.ext.Koin
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private const val countryBr = "br"
private const val languagePt = "pt"
const val countryDefault = countryBr
const val languageDefault = languagePt

@Suppress("unused") // Referenced in application.conf
fun Application.configuration() {
    install(ContentNegotiation) {
        gson {
        }
    }
    install(Koin) {
        modules(
           listOf(
               retrofitModules,
               serviceModules,
               repositoryModules,
               rssModule
           )
        )
    }
    routing {
        articles()
    }

    install(CallLogging) {
        level = Level.INFO
    }
}


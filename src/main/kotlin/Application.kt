import com.news.routes.articles
import com.news.routes.documentations
import com.news.service.ArticleService
import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.route.apiRouting
import com.papsign.ktor.openapigen.schema.namer.DefaultSchemaNamer
import com.papsign.ktor.openapigen.schema.namer.SchemaNamer
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.slf4j.event.Level
import kotlin.reflect.KType

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
    install(OpenAPIGen) {
        info {
            version = "0.0.1"
            title = "Dashboard app open api"
            description = "Dashboard is a new way for people to control their daily news, with reliable and unbiased sources."
            contact {
                name = "Tiago Casemiro"
                email = "tiagocasemiro@hotmail.com"
            }
        }
        server("https://soccer-news-gatway.herokuapp.com/") {
            description = "Dashboard api"
        }
        replaceModule(DefaultSchemaNamer, object: SchemaNamer {
            val regex = Regex("[A-Za-z0-9_.]+")
            override fun get(type: KType): String {
                return type.toString().replace(regex) { it.value.split(".").last() }.replace(Regex(">|<|, "), "_")
            }
        })
    }
    install(CallLogging) {
        level = Level.INFO
    }
    apiRouting {
        val articleService: ArticleService by inject()

        articles(articleService)
    }
    routing {
        documentations()
    }
}


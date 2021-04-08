import com.news.routes.articles
import com.news.routes.documentations
import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.route.apiRouting
import com.papsign.ktor.openapigen.schema.namer.DefaultSchemaNamer
import com.papsign.ktor.openapigen.schema.namer.SchemaNamer
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin
import kotlin.reflect.KType

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
    apiRouting {
        documentations()
    }
    routing {
        documentations()
        articles()
    }
}


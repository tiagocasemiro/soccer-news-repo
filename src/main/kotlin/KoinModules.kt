import com.news.repository.remote.elpais.ElPaisRepository
import com.news.repository.remote.google.ArticleRepository
import com.news.repository.remote.google.GoogleNewsApi
import com.news.repository.remote.google.HeaderInterceptor
import com.news.repository.remote.intercept.InterceptRepository
import com.news.repository.remote.nexo.NexoRepository
import com.news.repository.remote.techmundo.TechMundoRepository
import com.news.service.ArticleService
import com.rometools.rome.io.SyndFeedInput
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val retrofitModules = module {
    factory {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HeaderInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .build()
            )

    }

    factory {
        get<Retrofit.Builder>().baseUrl("http://newsapi.org").build().create(GoogleNewsApi::class.java) as GoogleNewsApi
    }
}

val rssModule = module() {
    factory {
        SyndFeedInput()
    }
}

val serviceModules = module {
    factory {
        ArticleService(articleRepository = get(), nexoRepository = get(), interceptRepository = get(), techMundoRepository = get(), elPaisRepository = get())
    }
}

val repositoryModules = module {
    factory {
        ArticleRepository(googleNewsApi = get())
    }
    factory {
        NexoRepository(syndFeedInput = get())
    }
    factory {
        InterceptRepository(syndFeedInput = get())
    }
    factory {
        TechMundoRepository(syndFeedInput = get())
    }
    factory {
        ElPaisRepository(syndFeedInput = get())
    }
}
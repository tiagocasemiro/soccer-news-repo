package com.soccer.news

import com.soccer.news.com.news.service.ArticleService
import com.soccer.news.com.news.repository.remote.ArticleRepository
import com.soccer.news.com.news.repository.remote.GoogleNewsApi
import com.soccer.news.com.news.repository.remote.HeaderInterceptor
import okhttp3.OkHttpClient
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
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .build()
            )

    }

    factory {
        get<Retrofit.Builder>().baseUrl("http://newsapi.org").build().create(GoogleNewsApi::class.java) as GoogleNewsApi
    }
}

val sericeModules = module {
    factory {
        ArticleService(articleRepository = get())
    }
}

val repositoryModules = module {
    factory {
        ArticleRepository(googleNewsApi = get())
    }
}
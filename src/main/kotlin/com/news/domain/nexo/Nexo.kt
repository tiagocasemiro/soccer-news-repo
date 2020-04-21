package com.news.domain.nexo

data class NexoArticle(
    val source: Source?, //dc:creator
    val title: String?,
    val link: String?,
    val pubdate: String?,
    val description: String?
)

data class Source(
    val name: String?, //NexoArticle
    val description: String?,
    val link: String?,
    val image: String? //image -> url
)
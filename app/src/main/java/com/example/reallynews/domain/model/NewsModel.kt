package com.example.reallynews.domain.model

import com.example.reallynews.data.api.response.Articles

data class NewsModel(
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String
)

fun Articles.toDomain(): NewsModel {
    return NewsModel(
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty()
    )
}

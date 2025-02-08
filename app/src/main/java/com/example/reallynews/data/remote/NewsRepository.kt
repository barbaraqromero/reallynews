package com.example.reallynews.data.remote

import com.example.reallynews.data.api.NewsApi
import com.example.reallynews.domain.model.NewsModel
import com.example.reallynews.domain.model.toDomain

class NewsRepository(
    private val api: NewsApi,
) {

    suspend fun getTopHeadlines(country: String, apiKey: String): Result<List<NewsModel>> {
        return try {
            val response = api.getTopHeadlines(country, apiKey)
            if (response.isSuccessful) {
                response.body()?.articles?.map { it.toDomain() }?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("erro"))
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    companion object {
        const val API_KEY = "578977554af4433aa58e4e553b57a24b"
        const val COUNTRY = "us"
    }
}
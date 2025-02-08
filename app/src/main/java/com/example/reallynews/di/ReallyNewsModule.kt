package com.example.reallynews.di

import com.example.reallynews.data.api.NewsApi
import com.example.reallynews.data.remote.NewsRepository
import com.example.reallynews.presentation.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule: Module = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    single { NewsRepository(get()) }
    viewModel { NewsViewModel(get()) }
}
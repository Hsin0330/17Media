package com.wind.githubuserssearch

import com.wind.githubuserssearch.repository.UserRepository
import com.wind.githubuserssearch.services.GithubService
import com.wind.githubuserssearch.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    factory { UserRepository() }
}

val networkModule = module {
    single { genetateOkHttpClient() }
    single { generateRetrofit(get()) }
    single { generateGithubSearchService(get()) }
}

fun genetateOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

fun generateRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(Constant.GithubUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun generateGithubSearchService(retrofit: Retrofit): GithubService =
    retrofit.create(GithubService::class.java)

val appModule = listOf(viewModelModule, networkModule, repositoryModule)
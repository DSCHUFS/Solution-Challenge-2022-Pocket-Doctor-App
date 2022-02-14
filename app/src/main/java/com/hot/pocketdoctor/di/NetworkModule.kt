package com.hot.pocketdoctor.di

import com.google.gson.GsonBuilder
import com.hot.pocketdoctor.data.api.UserAuthService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(AccessTokenInterceptor())
            .addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .build()
                )
            })
            .build()

    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .baseUrl("https://11.11.11.11")  // BaseUrl 확인
            .build()
    }

    single<UserAuthService> {
        get<Retrofit>().create(UserAuthService::class.java)
    }
}
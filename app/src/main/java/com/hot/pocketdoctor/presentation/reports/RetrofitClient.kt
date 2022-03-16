package com.hot.pocketdoctor.presentation.reports

import com.hot.pocketdoctor.di.AccessTokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "http://ec2-3-38-63-80.ap-northeast-2.compute.amazonaws.com:3000"

    fun interceptor(): OkHttpClient {
        return OkHttpClient.Builder()
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

    val instance : RetroService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(interceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetroService::class.java)
    }
}
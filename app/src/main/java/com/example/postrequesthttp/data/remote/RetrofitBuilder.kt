package com.example.postrequesthttp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val TIME_INTERVAL = 15L

    fun initRetrofit(baseUrl:String): RetrofitService{
        return Builder()
                .baseUrl(baseUrl)
                .client(intOkhttpClient(baseUrl))
                 .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
    }

    private fun intOkhttpClient(baseUrl: String): OkHttpClient {
        val okHttp = OkHttpClient.Builder()
                .addInterceptor(HeadersInterceptor()) // добавл класс интерсептор для получен защишен информации
                .authenticator(TokenAuthentificator(authApi(baseUrl)))  // добавл класс TokenAuthenticator для
                .writeTimeout(TIME_INTERVAL,TimeUnit.SECONDS)
                .readTimeout(TIME_INTERVAL,TimeUnit.SECONDS)
                .connectTimeout(TIME_INTERVAL,TimeUnit.SECONDS)
                okHttp.addInterceptor(provideLoginingInterceptor())  // использовать логирование!!

        return okHttp.build()
    }

    private fun authApi(baseUrl:String): AuthApi{  // этот метод для AUth
        return Builder()
                .client(
                        OkHttpClient.Builder()
                        .addInterceptor(provideLoginingInterceptor()).build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi::class.java)  // вызов интерфей AuthApi
    }

    private fun provideLoginingInterceptor(): HttpLoggingInterceptor {  // этот метод для интерсптора
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}
package com.example.postrequesthttp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val TIME_INTERVAL = 15L

    fun initRetrofit(baseUrl:String): RetrofitService{
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(intOkhttpClient())
                 .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
    }

    private fun intOkhttpClient(): OkHttpClient {
        val okHttp = OkHttpClient.Builder()
                .writeTimeout(TIME_INTERVAL,TimeUnit.SECONDS)
                .readTimeout(TIME_INTERVAL,TimeUnit.SECONDS)
                .connectTimeout(TIME_INTERVAL,TimeUnit.SECONDS)


        if (BuildConfig.DEBUG){ // если версия debug можно
            okHttp.addInterceptor(provideLoginingInterceptor())  // использовать логирование!!
        }
        return okHttp.build()
    }

    private fun provideLoginingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}
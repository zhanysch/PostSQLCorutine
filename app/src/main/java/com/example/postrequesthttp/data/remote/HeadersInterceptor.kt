package com.example.postrequesthttp.data.remote

import com.example.postrequesthttp.local.PrefsHelper
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {  // этот класс для того чтобы получить защишенные личные данные

    override fun intercept(chain: Interceptor.Chain): Response {  //метод подставляет токен из sharedPreferenc
        val token = PrefsHelper.geToken()
        val request = chain.request().newBuilder() // делаетс новый запрос
                if (token.isNotEmpty())   // проверка что токен не пустой
                request.addHeader("token",token)

        return chain.proceed(request.build())
    }
}
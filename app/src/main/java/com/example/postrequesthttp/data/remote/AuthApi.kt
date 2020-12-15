package com.example.postrequesthttp.data.remote

import com.example.postrequesthttp.data.model.TokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/users/auth")  // отправляем данные
    suspend fun login(@Body data:Map<String,String>): Response<TokenModel>
}
package com.example.postrequesthttp.data.remote

import com.example.postrequesthttp.data.model.AuthModel
import com.example.postrequesthttp.data.model.ProfileModel
import com.example.postrequesthttp.data.model.TokenModel
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @POST("api/v1/users/auth")  // отправляем данные
    suspend fun login(@Body data:Map<String,String>):Response<TokenModel>

    @POST("api/v1/users/auth")  // отправляем данные
    suspend fun loginDataClass(@Body data: AuthModel):Response<TokenModel>

    // login и  loginDataClass это 2 способа отправки данных в сервер

    @GET("api/v1/users/profile")
    suspend fun loadUserProfile():Response<ProfileModel?>


}
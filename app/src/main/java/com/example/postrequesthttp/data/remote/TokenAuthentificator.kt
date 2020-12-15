package com.example.postrequesthttp.data.remote

import com.example.postrequesthttp.data.model.TokenModel
import com.example.postrequesthttp.local.PrefsHelper
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route


// этот класс для обновления токена в фоне чтоб токен не сдох, т.к токен обновляется каждый 1.5 часа итд


class TokenAuthentificator(private val authApi: AuthApi) : okhttp3.Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {// метод сохраняет токен!!
        if (response.code == 401){
            PrefsHelper.saveToken("")  //обнул старый токен
            val result = refreshToken()
            return if (result.isSuccessful && result.body() !=null){
                val tokencheck = result.body()?.token?:""
                tokencheck?.let { PrefsHelper.saveToken(it) }  // обновляем токен!!!
                response.request.newBuilder()
                        .addHeader("token", tokencheck)
                        .build()
            } else{
                null
            }

        }
        return null
    }

    private fun refreshToken(): retrofit2.Response<TokenModel> {  // метод для обновления токена
         return runBlocking {
             val map = mapOf("email" to "rojsasha@gmail.com",
                     "password" to "fifa11alex")
             val result = authApi.login(map)

             return@runBlocking result
         }
    }
}
package com.example.postrequesthttp.interactors

import com.example.postrequesthttp.data.model.TokenModel
import com.example.postrequesthttp.data.remote.RetrofitService
import retrofit2.Response

interface RetrofitInteractor{
    suspend fun login(email:String,password:String): Response<TokenModel>

}

class RetrofitInteractorImpl(val service: RetrofitService) : RetrofitInteractor {

    override suspend fun login(email: String, password: String): Response<TokenModel> {

        val map = mapOf("email" to email,
        "password" to password
        )
        val result = service.login(map)             //login() из class RetrofitService()
        return result
    }
}
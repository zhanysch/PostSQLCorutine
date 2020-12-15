package com.example.postrequesthttp.repository

import com.example.postrequesthttp.data.model.ProfileModel
import com.example.postrequesthttp.data.model.TokenModel
import com.example.postrequesthttp.interactors.RetrofitInteractor
import com.example.postrequesthttp.local.PrefsHelper
import retrofit2.Response


interface RetrofitRepositories{
    suspend fun login(email:String,password:String): Response<TokenModel>
    suspend fun getProfile(): Response<ProfileModel?>

}

class RetrofitRepositoriesImpl(private val network : RetrofitInteractor): RetrofitRepositories {

    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val result = network.login(email = email,password = password)
        result.body()?.token?.let { PrefsHelper.saveToken(it) } // сохраням токен
        return result
    }

    override suspend fun getProfile(): Response<ProfileModel?> {
        return network.getProfile()
    }

}
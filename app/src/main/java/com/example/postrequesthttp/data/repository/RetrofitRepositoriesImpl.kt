package com.example.postrequesthttp.data.repository

import androidx.lifecycle.LiveData
import com.example.postrequesthttp.data.interactors.RetrofitInteractor
import com.example.postrequesthttp.data.local.PrefsHelper
import com.example.postrequesthttp.data.local.ProfileDao
import com.example.postrequesthttp.data.model.ProfileModel
import com.example.postrequesthttp.data.model.TokenModel
import retrofit2.Response


interface RetrofitRepositories{
    suspend fun login(email:String,password:String): Response<TokenModel>
    suspend fun loadProfile()
    fun getProfile() : LiveData<ProfileModel>

}

class RetrofitRepositoriesImpl(private val network : RetrofitInteractor,
       private val profileDao: ProfileDao
       ): RetrofitRepositories {

    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val result = network.login(email = email,password = password)
        result.body()?.token?.let { PrefsHelper.saveToken(it) } // сохраням токен
        return result
    }

    override suspend fun loadProfile() {
        val result = network.getProfile()
        result.body()?.let { profileDao.saveProfile(it) }   //  profileDao.saveProfile(result.body()) б/д
    }

    override fun getProfile() = profileDao.getProfile()  // вызов сохраненн


}
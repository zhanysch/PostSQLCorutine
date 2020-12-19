package com.example.postrequesthttp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postrequesthttp.data.model.TokenModel
import com.example.postrequesthttp.data.repository.RetrofitRepositories
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private val repository: RetrofitRepositories):ViewModel() {

    val actionNewScreen = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()  //

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email = email, password = password)
            if (result.isSuccessful) actionNewScreen.postValue(true)
            else showError(result)
        }
    }

    private fun showError(result: Response<TokenModel>) {
        error.postValue("error")  // для тостика
    }
}
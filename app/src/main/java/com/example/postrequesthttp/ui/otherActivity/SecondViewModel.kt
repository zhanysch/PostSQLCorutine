package com.example.postrequesthttp.ui.otherActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postrequesthttp.data.model.ProfileModel
import com.example.postrequesthttp.repository.RetrofitRepositories
import kotlinx.coroutines.launch

class SecondViewModel(private val repository: RetrofitRepositories): ViewModel() {

    val data = MutableLiveData<ProfileModel>()
    val error = MutableLiveData<String>()

    fun loadUser(){
        viewModelScope.launch {
            kotlin.runCatching {
                  val result = repository.getProfile()
                if (result.isSuccessful)
                    data.postValue(result.body())
                else error.postValue(result.message())
            }.onFailure {
                error.postValue(it.localizedMessage) //если будет ошибка localizedMessage  будет возвращать ошибку в сервере

            }
        }
    }
}
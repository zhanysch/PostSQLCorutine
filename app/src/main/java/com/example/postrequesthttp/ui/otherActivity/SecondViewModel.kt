package com.example.postrequesthttp.ui.otherActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postrequesthttp.data.model.ProfileModel
import com.example.postrequesthttp.data.repository.RetrofitRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondViewModel(private val repository: RetrofitRepositories): ViewModel() {


    val error = MutableLiveData<String>()

    init {
        loadUser()
    }

    fun loadUser(){                                                      //(Dispatchers.IO) при room Объязательно
        viewModelScope.launch(Dispatchers.IO){
            kotlin.runCatching {
                val result = repository.loadProfile()

            }.onFailure {
                error.postValue(it.localizedMessage) //если будет ошибка localizedMessage  будет возвращать ошибку в сервере

            }
        }
    }

    fun getProfileModel() = repository.getProfile()
}
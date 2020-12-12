package com.example.postrequesthttp.di

import com.example.postrequesthttp.BuildConfig
import com.example.postrequesthttp.data.remote.RetrofitBuilder
import com.example.postrequesthttp.interactors.RetrofitInteractor
import com.example.postrequesthttp.interactors.RetrofitInteractorImpl
import com.example.postrequesthttp.repository.RetrofitRepositories
import com.example.postrequesthttp.repository.RetrofitRepositoriesImpl
import com.example.postrequesthttp.ui.main.AuthViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module{
    viewModel{AuthViewModel(get())}
}
val repositoryModule = module{
    single { RetrofitBuilder.initRetrofit(BuildConfig.BASE_URL) } //base_url="https://api.sambo.beta.trinitydigital.ru/"
    single <RetrofitInteractor> { RetrofitInteractorImpl(get()) }
    single <RetrofitRepositories> { RetrofitRepositoriesImpl(get()) }
}
val appModules = listOf(viewModelModule, repositoryModule)
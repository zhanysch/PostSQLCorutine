package com.example.postrequesthttp.di

import androidx.room.Room
import com.example.postrequesthttp.BuildConfig
import com.example.postrequesthttp.data.remote.RetrofitBuilder
import com.example.postrequesthttp.data.interactors.RetrofitInteractor
import com.example.postrequesthttp.data.interactors.RetrofitInteractorImpl
import com.example.postrequesthttp.data.local.AppDataBase
import com.example.postrequesthttp.data.local.AppDataBase.Companion.MIGTRATION_1_2
import com.example.postrequesthttp.data.local.DATABASE_NAME
import com.example.postrequesthttp.data.local.ProfileDao
import com.example.postrequesthttp.data.repository.RetrofitRepositories
import com.example.postrequesthttp.data.repository.RetrofitRepositoriesImpl
import com.example.postrequesthttp.ui.main.AuthViewModel
import com.example.postrequesthttp.ui.otherActivity.SecondViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module{
    viewModel{AuthViewModel(get())}
    viewModel{SecondViewModel(get())}
}
val repositoryModule = module{
    single { RetrofitBuilder.initRetrofit(BuildConfig.BASE_URL) } //base_url="https://api.sambo.beta.trinitydigital.ru/"
    single <RetrofitInteractor> { RetrofitInteractorImpl(get()) }
    single <RetrofitRepositories> { RetrofitRepositoriesImpl(get(),get()) }
    single { Room.databaseBuilder(get(),
        AppDataBase::class.java,
        DATABASE_NAME)
        /*.fallbackToDestructiveMigration()  /// это 1 метод для миграции при изминении Б/д version и при изминении внутри дата класса*/
        .addMigrations(MIGTRATION_1_2 )
        .build() } //вытаскив Б/д
    single { get<AppDataBase>().profileDao() }  //вытягив Dao
}
val appModules = listOf(viewModelModule, repositoryModule)
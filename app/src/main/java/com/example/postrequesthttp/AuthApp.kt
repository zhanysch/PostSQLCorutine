package com.example.postrequesthttp

import android.app.Application
import com.example.postrequesthttp.data.local.AppDataBase
import com.example.postrequesthttp.di.appModules
import com.example.postrequesthttp.data.local.PrefsHelper
import org.koin.android.ext.android.startKoin

class AuthApp: Application() {

    private var db: AppDataBase? = null

    override fun onCreate() {
        super.onCreate()
        startKoin(this,appModules)
        PrefsHelper.init(this)
    }

}
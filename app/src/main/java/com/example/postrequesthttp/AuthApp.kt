package com.example.postrequesthttp

import android.app.Application
import com.example.postrequesthttp.di.appModules
import com.example.postrequesthttp.local.PrefsHelper
import org.koin.android.ext.android.startKoin

class AuthApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this,appModules)
        PrefsHelper.init(this)
    }

}
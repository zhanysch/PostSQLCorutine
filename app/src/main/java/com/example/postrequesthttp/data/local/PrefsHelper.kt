package com.example.postrequesthttp.data.local

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {


    private const val PREFS_NAME = "com.example.postrequesthttp"
    private const val TOKEN = "TOKEN"
    private lateinit var prefs: SharedPreferences


    fun init(context : Context){
        prefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
    }

    fun saveToken(token: String){
        prefs.edit().putString(TOKEN,token).apply()

    }

    fun geToken():String{
       return prefs.getString(TOKEN,"") ?:""

    }
}
package com.example.pruebatecnica

import android.app.Application
import com.example.pruebatecnica.util.Constants.BASE_URL
import com.example.retrofitlib.di.NetworkModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JokesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkModule.init(BASE_URL)
    }
}
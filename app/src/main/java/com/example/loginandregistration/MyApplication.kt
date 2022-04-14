package com.example.loginandregistration

import android.app.Application
import com.example.loginandregistration.di.AppModule
import com.example.loginandregistration.di.ApplicationComponent
import com.example.loginandregistration.di.DaggerApplicationComponent
import com.example.loginandregistration.di.NetworkManager


class MyApplication: Application() {

    companion object{
        lateinit var appComponent: ApplicationComponent
    }


    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerApplicationComponent.builder()
            .networkManager(NetworkManager)
            .appModule(AppModule(this))
            .build()
    }
}
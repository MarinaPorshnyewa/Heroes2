package com.example.loginandregistration.di

import android.content.Context
import com.example.loginandregistration.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val app: MyApplication
) {
    @Provides
    @Singleton
    fun provideApp(): MyApplication = app

    @Provides
    @Singleton
    fun provideContext(): Context = app
}
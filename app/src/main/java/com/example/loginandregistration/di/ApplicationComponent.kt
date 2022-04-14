package com.example.loginandregistration.di

import com.example.loginandregistration.ui.favorite.FavoriteFragment
import com.example.loginandregistration.ui.heroes.DisneyHeroesFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkManager::class, AppModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(fragment: DisneyHeroesFragment)
    fun inject2(fragment: FavoriteFragment)
}
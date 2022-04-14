package com.example.loginandregistration.ui.heroes

import com.example.loginandregistration.di.NetworkManager
import com.example.loginandregistration.repository.HeroesRepository
import com.zamana.lesson29.paging.DisneyDataSource
import com.zamana.myapplication.ui.base.BaseViewModelFactory
import javax.inject.Inject

class HeroesViewModelFactory @Inject constructor(
    private val dataSource: DisneyDataSource,
    private val heroesRepository: HeroesRepository
) : BaseViewModelFactory<HeroesViewModel>(HeroesViewModel::class.java) {

    override fun createViewModel(): HeroesViewModel {
        return HeroesViewModel(
            heroesRepository,
            dataSource
        )
    }

}
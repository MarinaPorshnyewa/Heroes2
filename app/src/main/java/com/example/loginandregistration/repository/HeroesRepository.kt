package com.example.loginandregistration.repository

import android.content.Context
import androidx.core.content.edit
import com.example.loginandregistration.di.NetworkManager
import com.example.loginandregistration.network.ApiService
import javax.inject.Inject


class HeroesRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getHeroes(page: String?) =
        apiService.getList(page)

    suspend fun getHeroesFavorite() =
        apiService.getList()

    //suspend fun getHeroes2(hId: Int, list: ArrayList<Data>) =
    //manager.provideUnauthorizedCachedRequestsApi().getList().body()?.data?.filter {
    //list.contains(it._id)
    //}
}
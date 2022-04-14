package com.example.loginandregistration.network

import com.example.loginandregistration.model.DisneyHeroes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/characters")
    suspend fun getList(
        @Query("page") page: String? = null
    ): Response<DisneyHeroes>

    @GET("/characters")
    suspend fun getListFavorite(): Response<DisneyHeroes>
}
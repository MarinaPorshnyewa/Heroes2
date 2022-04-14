package com.zamana.lesson29.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.repository.HeroesRepository
import java.lang.Exception
import javax.inject.Inject

class DisneyDataSource @Inject constructor(
    private val repository: HeroesRepository
): PagingSource<Int, Data>() {

    private var totalPages = 0

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Data> {


        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getHeroes(nextPageNumber.toString())
            totalPages = response.body()?.totalPages ?: 0
            if (totalPages < nextPageNumber + 1) {
                LoadResult.Page(
                    data = response.body()?.data ?: arrayListOf(),
                    prevKey = null,
                    nextKey = null
                )

            } else {
                LoadResult.Page(
                    data = response.body()?.data ?: arrayListOf(),
                    prevKey = null,
                    nextKey = nextPageNumber + 1
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

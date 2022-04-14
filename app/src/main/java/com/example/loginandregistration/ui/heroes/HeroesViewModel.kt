package com.example.loginandregistration.ui.heroes


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.repository.HeroesRepository
import com.zamana.lesson29.paging.DisneyDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroesViewModel(
    private val repository: HeroesRepository,
    private val dataSource: DisneyDataSource
) : ViewModel() {

    lateinit var loadImages: (list: ArrayList<Data>) -> Unit

    lateinit var loadImages2: (list: List<Data>) -> Unit

    lateinit var showProgress: (isShow: Boolean) -> Unit

    val result = Pager(PagingConfig(20), 1) {
        dataSource
    }.flow.cachedIn(viewModelScope)

    /*fun getHeroesList() {
        viewModelScope.launch(Dispatchers.IO) {
            showProgress(true)
            val response = repository.getHeroes()

            if (response.isSuccessful) {
                response.body()?.let {

                    loadImages(it.data)
                }
            } else {
                response.errorBody()
            }
            showProgress(false)
        }
    }*/

    fun getHeroesOnID(hId: Set<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            showProgress(true)
            val response = repository.getHeroesFavorite()

            if (response.isSuccessful) {
                response.body()?.data?.filter {
                    hId.contains(it._id.toString())


                }.let {

                    loadImages2(it!!)
                }
            } else {
                response.errorBody()
            }
            showProgress(false)
        }
    }
}
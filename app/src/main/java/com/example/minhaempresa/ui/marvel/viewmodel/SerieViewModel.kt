package com.example.minhaempresa.ui.marvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.minhaempresa.ui.marvel.datasource.SerieDataSource
import com.example.minhaempresa.ui.marvel.initPagingConfi
import com.example.minhaempresa.ui.marvel.response.serie.Series
import com.example.minhaempresa.ui.marvel.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

class SerieViewModel(private val api: API) : ViewModel() {

//    val job = SupervisorJob()
//    val scope = CoroutineScope(Dispatchers.Default + job)

    var seriesLiveData : LiveData<PagedList<Series>>

    init {
        seriesLiveData = initPagedListBuild(initPagingConfi()).build()
    }

    fun getSeries() : LiveData<PagedList<Series>> = seriesLiveData


    fun initPagedListBuild(config: PagedList.Config) : LivePagedListBuilder<Int, Series> {
        val dataSourceFactory = object : DataSource.Factory<Int, Series>() {
            override fun create(): DataSource<Int, Series> {
                return SerieDataSource(api)
            }
        }
        return LivePagedListBuilder<Int, Series>(dataSourceFactory, config)
    }

//    override fun onCleared() {
//        super.onCleared()
//        job.cancel()
//    }
}
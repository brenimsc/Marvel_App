package com.example.minhaempresa.ui.marvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.minhaempresa.ui.marvel.datasource.ComicDataSource
import com.example.minhaempresa.ui.marvel.initPagingConfi
import com.example.minhaempresa.ui.marvel.response.comic.Comic
import com.example.minhaempresa.ui.marvel.retrofit.API

class CreatorViewModel(val api: API) : ViewModel() {

    var comicLiveData: LiveData<PagedList<Comic>>

    init {
        comicLiveData = initPagedListBuild(initPagingConfi()).build()
    }


    fun getCreators(): LiveData<PagedList<Comic>> {
        return comicLiveData
    }


    fun initPagedListBuild(config: PagedList.Config): LivePagedListBuilder<Int, Comic>{
        val dataSourceFactory = object : DataSource.Factory<Int, Comic>(){
            override fun create(): DataSource<Int, Comic> {
                return ComicDataSource(api)
            }
        }
        return LivePagedListBuilder<Int,Comic>(dataSourceFactory, config)
    }
}
package com.example.minhaempresa.ui.marvel.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.minhaempresa.ui.marvel.datasource.PersonagemDataSource
import com.example.minhaempresa.ui.marvel.initPagingConfi
import com.example.minhaempresa.ui.marvel.repository.PersonagemComicRepository
import com.example.minhaempresa.ui.marvel.response.Character
import com.example.minhaempresa.ui.marvel.response.comic.ResponseComic
import com.example.minhaempresa.ui.marvel.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PersonagemViewModel(private val api: API, private val repository: PersonagemComicRepository) : ViewModel() {

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.IO + job)

    private var _comicsPersonagens: MutableLiveData<ResponseComic> = MutableLiveData()
    val comicsPersonagens : LiveData<ResponseComic> = _comicsPersonagens
    var personagensLiveData : LiveData<PagedList<Character>>

    init {
        personagensLiveData = initPagedListBuild(initPagingConfi()).build()
    }

    fun getPersonagens(): LiveData<PagedList<Character>> = personagensLiveData

    fun getComics(id: Int){
        scope.launch {
            try{
                val dados = repository.getComic(id).await()
                _comicsPersonagens.postValue(dados)
            } catch (t: Throwable) {
                Log.d("Comics", "Erro na busca Comic"+t.message)
            }
        }
    }


    fun initPagedListBuild(config: PagedList.Config): LivePagedListBuilder<Int, Character> {
        val dataSourceFactory = object : DataSource.Factory<Int, Character>() {
            override fun create(): DataSource<Int, Character> {
                return PersonagemDataSource(api)
            }
        }
        return LivePagedListBuilder<Int, Character>(dataSourceFactory, config)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
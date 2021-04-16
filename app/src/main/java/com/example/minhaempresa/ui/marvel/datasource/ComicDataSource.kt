package com.example.minhaempresa.ui.marvel.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.minhaempresa.ui.marvel.response.comic.Comic
import com.example.minhaempresa.ui.marvel.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class ComicDataSource(private val api: API) : PageKeyedDataSource<Int, Comic>() {

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Comic>) {
        request(0, callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Comic>) {
        request(params.key+1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Comic>) {
        request(params.key-1, null, callback)
    }

    fun request(pageKey: Int, initialCallback : LoadInitialCallback<Int, Comic>?, callback: LoadCallback<Int, Comic>?) {
        scope.launch {
            try {
                val response = api.getAllComics(pageKey*10).await()
                when {
                    response.isSuccessful -> {
                        val list = response.body()?.data?.results?.map { it }
                        val listaComics = list ?: listOf()

                        initialCallback?.onResult(listaComics, null, pageKey)
                        callback?.onResult(listaComics, pageKey)
                    } else -> {
                    Log.d("ComicAll" ,"Sem sucesso")
                }
                }

            } catch (e: Exception){
                Log.d("Deu ruim", "ComicAll")
            }
        }
    }
}
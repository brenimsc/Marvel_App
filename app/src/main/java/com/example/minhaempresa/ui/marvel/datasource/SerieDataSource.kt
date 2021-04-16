package com.example.minhaempresa.ui.marvel.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.minhaempresa.ui.marvel.response.serie.Series
import com.example.minhaempresa.ui.marvel.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class SerieDataSource(private val api: API) : PageKeyedDataSource<Int, Series>() {

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Series>) {
        request(0, callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Series>) {
        request(params.key+1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Series>) {
        request(params.key-1, null, callback)
    }

    private fun request(pageKey: Int, initialCallback: LoadInitialCallback<Int, Series>?, callback: LoadCallback<Int, Series>?) {
        scope.launch {
            try {
                val response = api.getSeries(pageKey*10).await()
                when {
                    response.isSuccessful -> {
                        val list = response.body()?.data?.results?.map { it }
                        val listaSeries = list ?: listOf()

                        initialCallback?.onResult(listaSeries, null, pageKey)
                        callback?.onResult(listaSeries, pageKey)
                    } else -> {
                    Log.d("Não deu", "Sem sucesso na response")
                }
                }

            } catch (e: Exception){
                Log.d("Não deu ", "Requisição da lista")
            }
        }

    }
}
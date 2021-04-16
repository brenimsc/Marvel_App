package com.example.minhaempresa.ui.marvel.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.minhaempresa.ui.marvel.response.Character
import com.example.minhaempresa.ui.marvel.retrofit.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PersonagemDataSource(private val api: API) : PageKeyedDataSource<Int, Character>() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>
    ) {
        request(0, callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        request(params.key+1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        request(params.key-1, null, callback)
    }

    fun request(pageKey: Int, initialCallback: LoadInitialCallback<Int, Character>?, callback: LoadCallback<Int, Character>?){
        scope.launch {
            try {
                val response = api.getPersonagens(offset = pageKey*10).await()
                when {
                    response.isSuccessful ->{
                        val listing = response.body()
                        val personagens = listing?.data?.results?.map { it }
                        val lista = personagens ?: listOf()

                        initialCallback?.onResult(lista, null, pageKey)
                        callback?.onResult(lista, pageKey)

                    }
                    else -> {
                        Log.e("PersonagemDataSource", "Nao Sucesso")
                    }
                }
            } catch (e: Exception) {
                Log.e("PersonagemDataSource", "Nao conseguiu no try")
            }
        }
    }
}
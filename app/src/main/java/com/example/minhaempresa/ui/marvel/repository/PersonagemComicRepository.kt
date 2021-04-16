package com.example.minhaempresa.ui.marvel.repository

import com.example.minhaempresa.ui.marvel.response.comic.ResponseComic
import com.example.minhaempresa.ui.marvel.retrofit.API
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class PersonagemComicRepository(private val api: API) {

    suspend fun getComic(id: Int): Deferred<ResponseComic> {
        return withContext(IO) {
            async { api.getComics(id).await() }
        }
    }
}
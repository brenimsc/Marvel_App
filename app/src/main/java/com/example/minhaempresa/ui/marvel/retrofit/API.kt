package com.example.minhaempresa.ui.marvel.retrofit

import com.example.minhaempresa.ui.marvel.response.ResponseCharacter
import com.example.minhaempresa.ui.marvel.response.comic.ResponseComic
import com.example.minhaempresa.ui.marvel.response.creator.ResponseCreator
import com.example.minhaempresa.ui.marvel.response.serie.ResponseSerie
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("v1/public/characters?ts=1&limit=10&apikey=f019de9971099148da138d081a9d0292&hash=4fab64af341526d000aa81509a37114b")
    fun getPersonagens(@Query("offset")offset: Int = 0): Deferred<retrofit2.Response<ResponseCharacter>>

    @GET("v1/public/characters/{characterId}/comics?ts=1&apikey=f019de9971099148da138d081a9d0292&hash=4fab64af341526d000aa81509a37114b")
    fun getComics(@Path("characterId") id: Int): Deferred<ResponseComic>

    @GET("v1/public/series?ts=1&limit=10&apikey=f019de9971099148da138d081a9d0292&hash=4fab64af341526d000aa81509a37114b")
    fun getSeries(@Query("offset")offset: Int =0): Deferred<retrofit2.Response<ResponseSerie>>

    @GET("v1/public/comics?ts=1&limit=10&apikey=f019de9971099148da138d081a9d0292&hash=4fab64af341526d000aa81509a37114b")
    fun getAllComics(@Query("offset")offset: Int = 0) : Deferred<retrofit2.Response<ResponseComic>>



}

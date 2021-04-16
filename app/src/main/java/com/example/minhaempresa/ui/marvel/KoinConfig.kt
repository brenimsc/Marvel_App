package com.example.minhaempresa.ui.marvel

import com.example.minhaempresa.ui.marvel.repository.PersonagemComicRepository
import com.example.minhaempresa.ui.marvel.retrofit.API
import com.example.minhaempresa.ui.marvel.retrofit.createApi
import com.example.minhaempresa.ui.marvel.retrofit.provideOkHttpClient
import com.example.minhaempresa.ui.marvel.retrofit.provideRetrofit
import com.example.minhaempresa.ui.marvel.viewmodel.CreatorViewModel
import com.example.minhaempresa.ui.marvel.viewmodel.EstadoAppViewModel
import com.example.minhaempresa.ui.marvel.viewmodel.PersonagemViewModel
import com.example.minhaempresa.ui.marvel.viewmodel.SerieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<API>(get())}
}

val uiModule = module {
    viewModel { PersonagemViewModel(get(), get()) }
    viewModel { SerieViewModel(get()) }
    viewModel { EstadoAppViewModel() }
    viewModel { CreatorViewModel(get()) }
}

val repoModule = module {
    single {PersonagemComicRepository(get())}
}
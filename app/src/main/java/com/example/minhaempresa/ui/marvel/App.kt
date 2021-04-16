package com.example.minhaempresa.ui.marvel

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(uiModule, remoteModule, repoModule).androidContext(this@App)
        }
    }

}
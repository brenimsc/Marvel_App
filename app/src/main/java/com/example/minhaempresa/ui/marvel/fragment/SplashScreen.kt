package com.example.minhaempresa.ui.marvel.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.minhaempresa.ui.marvel.MainActivity
import com.example.minhaempresa.ui.marvel.R

class SplashScreen : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
            Handler().postDelayed(Runnable {
                kotlin.run {
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                    finish()
                }
            }, 2000)

    }
    }

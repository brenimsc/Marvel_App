package com.example.minhaempresa.ui.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.minhaempresa.ui.marvel.viewmodel.EstadoAppViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val controlador by lazy {
        findNavController(R.id.nav_host)
    }
    private val viewModel: EstadoAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        controlador.addOnDestinationChangedListener{ controller, destination, arguments ->
            title = destination.label

            viewModel.componentes.observe(this, Observer {
                it?.let {temComponentes ->
                    if (temComponentes.appBar)
                        supportActionBar?.show()
                    else
                        supportActionBar?.hide()

                    if (temComponentes.bottomNavigation)
                        main_activity_bottom_navigation.visibility = VISIBLE
                    else
                        main_activity_bottom_navigation.visibility = GONE
                }
            })
        }

        main_activity_bottom_navigation.setupWithNavController(controlador)
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
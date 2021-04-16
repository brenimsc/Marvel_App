package com.example.minhaempresa.ui.marvel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minhaempresa.ui.marvel.R
import com.example.minhaempresa.ui.marvel.adapter.ListaSeriesAdapter
import com.example.minhaempresa.ui.marvel.viewmodel.ComponentesVisuais
import com.example.minhaempresa.ui.marvel.viewmodel.EstadoAppViewModel
import com.example.minhaempresa.ui.marvel.viewmodel.SerieViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.fragment_serie_lista.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaSeriesFragment : Fragment() {

    private val viewModel: SerieViewModel by viewModel()
    private val estadoViewModel: EstadoAppViewModel by sharedViewModel()
    private lateinit var shimmer: ShimmerFrameLayout
    private val adapter: ListaSeriesAdapter = ListaSeriesAdapter(::shimmerStop)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_serie_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewModel.temComponentes = ComponentesVisuais(bottomNavigation = true)
        shimmer = view.findViewById(R.id.placeholder)
        recyclerListaSerie.layoutManager = LinearLayoutManager(context)

        viewModel.getSeries().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            recyclerListaSerie.adapter = adapter
        })
    }

    fun shimmerStop() {
        shimmer.visibility = GONE
    }

    override fun onPause() {
        super.onPause()
        shimmer.visibility = GONE
    }

}
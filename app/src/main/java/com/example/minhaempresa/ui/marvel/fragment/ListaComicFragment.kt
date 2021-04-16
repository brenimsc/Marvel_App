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
import com.example.minhaempresa.ui.marvel.adapter.ListaComicAdapter
import com.example.minhaempresa.ui.marvel.viewmodel.ComponentesVisuais
import com.example.minhaempresa.ui.marvel.viewmodel.CreatorViewModel
import com.example.minhaempresa.ui.marvel.viewmodel.EstadoAppViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.fragment_serie_lista.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaComicFragment : Fragment() {

    private val viewModel: CreatorViewModel by viewModel()
    private lateinit var shimmer: ShimmerFrameLayout
    private val adapter: ListaComicAdapter = ListaComicAdapter(::shimmerStop)
    private val estadoAppViewModel: EstadoAppViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_serie_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmer = view.findViewById(R.id.placeholder)
        estadoAppViewModel.temComponentes = ComponentesVisuais(bottomNavigation = true)
        recyclerListaSerie.layoutManager = LinearLayoutManager(context)
        viewModel.getCreators().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            recyclerListaSerie.adapter = adapter
        })
    }

    override fun onPause() {
        super.onPause()
        shimmer.visibility = GONE
    }

    fun shimmerStop(){
        shimmer.visibility = GONE
    }

}
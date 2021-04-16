package com.example.minhaempresa.ui.marvel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minhaempresa.ui.marvel.ListaPersonagemAdapter
import com.example.minhaempresa.ui.marvel.R
import com.example.minhaempresa.ui.marvel.viewmodel.ComponentesVisuais
import com.example.minhaempresa.ui.marvel.viewmodel.EstadoAppViewModel
import com.example.minhaempresa.ui.marvel.viewmodel.PersonagemViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.fragment_personagem_lista.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaPersonagemFragment: Fragment() {

    private val viewModel: PersonagemViewModel by viewModel()
    private val estadoViewModel: EstadoAppViewModel by sharedViewModel()
    private val personagemAdapter: ListaPersonagemAdapter = ListaPersonagemAdapter(::navegaComId, ::shimmerStop)
    private val controlador by lazy {
        findNavController()
    }
    private lateinit var shimmer: ShimmerFrameLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personagem_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewModel.temComponentes = ComponentesVisuais(bottomNavigation = true)
        shimmer = view.findViewById(R.id.placeholder)
        recyclerListaPersonagens.layoutManager = LinearLayoutManager(requireContext())
        getObserver()
    }

    private fun getObserver(){
        viewModel.getPersonagens().observe(viewLifecycleOwner, Observer {
            personagemAdapter.submitList(it)
            recyclerListaPersonagens.adapter = personagemAdapter
        })
    }

    override fun onPause() {
        super.onPause()
        shimmer.visibility = GONE
    }

    fun navegaComId(id: Int){
        val direcaoComId = ListaPersonagemFragmentDirections.actionPersonagemListaFragmentToPersonagemComicFragment(id)
        controlador.navigate(direcaoComId)
    }

    private fun shimmerStop() {
        shimmer.visibility = GONE
    }
}
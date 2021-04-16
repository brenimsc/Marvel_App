package com.example.minhaempresa.ui.marvel.fragment

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minhaempresa.ui.marvel.ListaQuadrinhosAdapter
import com.example.minhaempresa.ui.marvel.R
import com.example.minhaempresa.ui.marvel.viewmodel.ComponentesVisuais
import com.example.minhaempresa.ui.marvel.viewmodel.EstadoAppViewModel
import com.example.minhaempresa.ui.marvel.viewmodel.PersonagemViewModel
import kotlinx.android.synthetic.main.fragment_personagem_comic.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaPersonagemComicFragment: Fragment() {

    private val viewModel: PersonagemViewModel by viewModel()
    private val estadoViewModel: EstadoAppViewModel by sharedViewModel()
    private lateinit var adapter: ListaQuadrinhosAdapter
    private val argumentos by navArgs<ListaPersonagemComicFragmentArgs>()
    private val characterId by lazy {
        argumentos.characterId
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_personagem_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerComic.layoutManager = LinearLayoutManager(requireContext())
        estadoViewModel.temComponentes = ComponentesVisuais(bottomNavigation = false)
        viewModel.getComics(characterId)
        viewModel.comicsPersonagens.observeForever {
            adapter = ListaQuadrinhosAdapter(it.data.results,::abreUrl)
            recyclerComic.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_pesquisa_comic, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menuPesquisa)
        if (searchItem != null) {
            val searchView = MenuItemCompat.getActionView(searchItem) as androidx.appcompat.widget.SearchView
            searchView.setOnCloseListener { true } //quando clica fecha o icone

            val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
            // abre um edit de pesquisa
            searchPlate.hint = "Digite o titulo" //sobrescreve o buscar
            val searchPlateView: View = searchView.findViewById(androidx.appcompat.R.id.search_plate)
            searchPlateView.setBackgroundColor( //setar a cor do fundo
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.transparent// cor transparente
                )
            )

            searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })

            val searchManager =
                activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }
        return super.onCreateOptionsMenu(menu,inflater)
    }


    private fun abreUrl(url: String) {
        startActivity(Intent(ACTION_VIEW, Uri.parse(url)))
    }



}
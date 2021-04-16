package com.example.minhaempresa.ui.marvel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaempresa.ui.marvel.R
import com.example.minhaempresa.ui.marvel.diffutil.DiffUtilCallbackComic
import com.example.minhaempresa.ui.marvel.response.comic.Comic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_item.view.*

class ListaComicAdapter(private var quandoCarregado: () -> Unit) : PagedListAdapter<Comic, ListaComicAdapter.ViewHolder>(DiffUtilCallbackComic()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCriada = LayoutInflater.from(parent.context).inflate(R.layout.comic_item, parent, false)
        return ViewHolder(viewCriada, quandoCarregado)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = getItem(position)
        comic?.let {
            holder.vincula(it)
        }

    }



    inner class ViewHolder(itemView: View, private val quandoCarregado: () -> Unit) : RecyclerView.ViewHolder(itemView){
        private val img = itemView.itemComicImagem
        private val descricao = itemView.itemComicDescricao
        private val criadoresTotal = itemView.itemComicTotalCriadores
        private val criadorPrincipal = itemView.itemComicCriadoPrincipal
        private val price = itemView.itemComicPreco
        private val titulo = itemView.itemComicTitulo

        fun vincula(item: Comic) {
            if(!item.thumbnail.path.contains("not_available"))
            Picasso.get().load("${item.thumbnail.path}.${item.thumbnail.extension}").fit().centerCrop().into(img)
            if (item.description != null) {
                descricao.text = item.description
            } else
                descricao.text = "(Sem descrição)"
            criadoresTotal.text = item.creators.available.toString()
            criadorPrincipal.text = if(item.creators.items.size > 0) "${item.creators.items[0].name}" else ""
            price.text =  if(item.prices.size > 0) "U$ ${item.prices[0].price}" else ""
            titulo.text = item.title
            quandoCarregado()
        }

    }


}
package com.example.minhaempresa.ui.marvel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaempresa.ui.marvel.R
import com.example.minhaempresa.ui.marvel.diffutil.DiffUtilCallbackSerie
import com.example.minhaempresa.ui.marvel.response.serie.Series
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.serie_item.view.*

class ListaSeriesAdapter(private var quandoCarregado: () -> Unit) : PagedListAdapter<Series, ListaSeriesAdapter.ViewHolder>(DiffUtilCallbackSerie()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCriada = LayoutInflater.from(parent.context).inflate(R.layout.serie_item, parent, false)
        return ViewHolder(viewCriada, quandoCarregado)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val serie = getItem(position)
        serie?.let { serie ->
            holder.vincula(serie)
        }

    }



    inner class ViewHolder(itemView: View, private val quandoCarregado: () -> Unit) : RecyclerView.ViewHolder(itemView){
        private val img = itemView.itemSerieImagem
        private val titulo = itemView.itemSerieTitulo
        private val descricao = itemView.itemSerieDescricao
        private val start = itemView.itemSerieInicio
        private val end = itemView.itemSerieFim

        fun vincula(item: Series) {
            if(!item.thumbnail.path.contains("not_available"))
            Picasso.get().load("${item.thumbnail.path}.${item.thumbnail.extension}").fit().centerCrop().into(img)
            titulo.text = item.title
            if (item.description!=null){
                descricao.text = item.description
            } else{
                descricao.text = "(Sem descrição)"
            }
            start.text = item.startYear.toString()
            end.text = item.endYear.toString()
            quandoCarregado()
        }


    }



}
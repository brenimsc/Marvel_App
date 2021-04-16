package com.example.minhaempresa.ui.marvel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaempresa.ui.marvel.response.comic.Comic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.personagem_comic_item.view.*

class ListaQuadrinhosAdapter(private val list: List<Comic>, private val mandaUrl: (String) -> Unit) : RecyclerView.Adapter<ListaQuadrinhosAdapter.ViewHolder>(), Filterable{

    var listaFiltrada: List<Comic>
    init {
        listaFiltrada = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCriada = LayoutInflater.from(parent.context).inflate(R.layout.personagem_comic_item, parent, false)
        return ViewHolder(viewCriada, mandaUrl)
    }

    override fun getItemCount(): Int {
        return listaFiltrada.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(listaFiltrada[position])
    }

    inner class ViewHolder(itemView: View, mandaUrl: (String) -> Unit) : RecyclerView.ViewHolder(itemView){
        private val titulo = itemView.itemComicTitulo
        private val descricao = itemView.itemComicDescricao
        private val img = itemView.itemComicImagem
        private val preco = itemView.itemComicPreco
        private val detalhes = itemView.itemComicDetalhes

        fun vincula(item: Comic){
            titulo.text = item.title
            if(item.description!=null)
                descricao.text = item.description
            else
                descricao.text = "(Sem descrição)"
            if(!item.thumbnail.path.contains("not_available"))
            Picasso.get().load("${item.thumbnail.path}.${item.thumbnail.extension}").fit().centerCrop().into(img)
            preco.text = if(item.prices.size > 0) "U$ ${item.prices[0].price}" else ""

            detalhes.setOnClickListener {
                mandaUrl(item.urls[0].url)
            }

        }


    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isNotEmpty()){
                    listaFiltrada = list.filter { it.title.toUpperCase().contains(constraint.toString().toUpperCase())}
                } else{
                    listaFiltrada = list
                }
                val resultados = FilterResults()
                resultados.values = listaFiltrada
                return resultados
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listaFiltrada = results?.values as List<Comic>
                notifyDataSetChanged()
            }

        }
    }


}
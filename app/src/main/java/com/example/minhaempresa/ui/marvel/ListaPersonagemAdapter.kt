package com.example.minhaempresa.ui.marvel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaempresa.ui.marvel.diffutil.DiffUtilCallback
import com.example.minhaempresa.ui.marvel.response.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.personagem_comic_item.view.*
import kotlinx.android.synthetic.main.personagem_item.view.*

class ListaPersonagemAdapter(private var onItemClickListener: (Int) -> Unit, private var quandoCarregado: () -> Unit) : PagedListAdapter<Character, ListaPersonagemAdapter.ViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListaPersonagemAdapter.ViewHolder {
        val viewCriada =
            LayoutInflater.from(parent.context).inflate(R.layout.personagem_item, parent, false)
        return ViewHolder(viewCriada, onItemClickListener, quandoCarregado)
    }

    override fun onBindViewHolder(holder: ListaPersonagemAdapter.ViewHolder, position: Int) {
        val personagem = getItem(position)
        personagem?.let {
            holder.vincula(it)
        }
    }

    inner class ViewHolder(itemView: View, private val listener:(Int) -> Unit, private val quandoCarregado: () -> Unit) : RecyclerView.ViewHolder(itemView){
        private val card = itemView.cardView
        private val img = itemView.itemPersonagemImagem
        private val nome = itemView.itemPersonagemNome
        private val modificacao = itemView.itemPersonagemModificacao


        fun vincula(item: Character){
            nome.setText(item.name)
            modificacao.setText(item.modified.formataData())
            if(!item.thumbnail.path.contains("not_available"))
            Picasso.get().load("${item.thumbnail.path}.${item.thumbnail.extension}").fit().centerCrop().into(img)
            card.setOnClickListener { listener(item.id) }
            quandoCarregado()
        }

        fun String.formataData(): String{
            return this.substring(0, 10).replace("-", "/")
        }
    }
}
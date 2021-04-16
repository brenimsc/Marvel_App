package com.example.minhaempresa.ui.marvel.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.minhaempresa.ui.marvel.response.comic.Comic
import com.example.minhaempresa.ui.marvel.response.creator.Creator

class DiffUtilCallbackComic : DiffUtil.ItemCallback<Comic>() {
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem == newItem
    }
}
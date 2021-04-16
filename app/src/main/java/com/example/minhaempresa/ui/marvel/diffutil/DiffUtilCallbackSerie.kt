package com.example.minhaempresa.ui.marvel.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.minhaempresa.ui.marvel.response.serie.Series

class DiffUtilCallbackSerie : DiffUtil.ItemCallback<Series>() {
    override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
        return oldItem == newItem
    }
}
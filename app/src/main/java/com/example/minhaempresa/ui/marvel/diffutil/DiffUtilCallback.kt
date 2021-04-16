package com.example.minhaempresa.ui.marvel.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.minhaempresa.ui.marvel.response.Character

class DiffUtilCallback : DiffUtil.ItemCallback<Character>(){
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}

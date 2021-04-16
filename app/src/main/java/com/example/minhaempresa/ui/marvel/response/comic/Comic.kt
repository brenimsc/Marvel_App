package com.example.minhaempresa.ui.marvel.response.comic

import com.example.minhaempresa.ui.marvel.response.Thumbnail
import com.example.minhaempresa.ui.marvel.response.creator.Creator

data class Comic(val id: Int,
                 val title: String,
                 val description: String,
                 val prices: List<Price>,
                 val thumbnail: Thumbnail,
                 val urls: List<Url>,
                 val creators: Creator) {

}

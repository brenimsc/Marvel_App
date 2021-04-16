package com.example.minhaempresa.ui.marvel.response.serie

import com.example.minhaempresa.ui.marvel.response.Thumbnail

 data class Series(val id: Int,
              val title: String,
              val description: String,
              val startYear: Int,
              val endYear: Int,
              val thumbnail: Thumbnail) {

}

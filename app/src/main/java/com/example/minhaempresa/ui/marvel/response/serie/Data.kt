package com.example.minhaempresa.ui.marvel.response.serie

data class Data(val offset: Int,
                val limit: Int,
                val total: Int,
                val results: List<Series>) {

}

package com.example.minhaempresa.ui.marvel.response.creator


data class DataCreator(val offset: Int,
                       val limit: Int,
                       val total: Int,
                       val count: Int,
                       val results: List<Creator>) {

}

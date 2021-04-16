package com.example.minhaempresa.ui.marvel

import androidx.paging.PagedList

internal fun initPagingConfi(pageSize: Int = 10,
                             enablePlaceHolder: Boolean = true,
                             prefetchDistance: Int = 5) =
    PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setEnablePlaceholders(enablePlaceHolder)
        .setPrefetchDistance(prefetchDistance)
        .build()
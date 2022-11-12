package com.xperiencelabs.krypto.presenter.news

import com.xperiencelabs.krypto.data.remote.dto.NewsEntity

data class NewsListState(
  val news:List<NewsEntity> = emptyList(),
  val error: String = "",
  val isLoading:Boolean = false
)
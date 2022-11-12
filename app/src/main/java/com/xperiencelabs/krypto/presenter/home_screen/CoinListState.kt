package com.xperiencelabs.krypto.presenter.home_screen

import com.xperiencelabs.krypto.data.remote.dto.TickerEntity
import com.xperiencelabs.krypto.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean = false,
    val coins: List<TickerEntity> = emptyList(),
    val error: String = ""

)
package com.xperiencelabs.krypto.presenter.home_screen

import com.xperiencelabs.krypto.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""

)
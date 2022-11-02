package com.xperiencelabs.krypto.presenter.currency_detail

import com.xperiencelabs.krypto.domain.model.Coin
import com.xperiencelabs.krypto.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""

)
package com.xperiencelabs.krypto.presenter.currency_detail

import com.xperiencelabs.krypto.data.remote.dto.TickerEntity
import com.xperiencelabs.krypto.domain.model.Coin
import com.xperiencelabs.krypto.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean = false,
    val coin: TickerEntity? = null,
    val error: String = ""

)
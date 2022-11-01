package com.xperiencelabs.krypto.domain.model

import com.google.gson.annotations.SerializedName
import com.xperiencelabs.krypto.data.remote.dto.CoinDTO

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,

)

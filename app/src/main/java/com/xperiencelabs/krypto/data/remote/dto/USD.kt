package com.xperiencelabs.krypto.data.remote.dto

data class USD(
    val ath_date: String,
    val ath_price: Double,
    val market_cap: Long,
    val market_cap_change_24h: Float,
    val percent_change_12h: Float,
    val percent_change_15m: Float,
    val percent_change_1h: Float,
    val percent_change_1y: Float,
    val percent_change_24h: Float,
    val percent_change_30d: Float,
    val percent_change_30m: Float,
    val percent_change_6h: Float,
    val percent_change_7d: Float,
    val percent_from_price_ath: Float,
    val price: Double,
    val volume_24h: Float,
    val volume_24h_change_24h: Float
)


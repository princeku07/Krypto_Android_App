package com.xperiencelabs.krypto.domain.model

import com.google.gson.annotations.SerializedName

//Global data entity
data class GlobalStats(
    @SerializedName("bitcoin_dominance_percentage")
    val btcDominancePercentage: Double,
    @SerializedName("cryptocurrencies_number")
    val cryptocurrenciesAmount: Int,
    @SerializedName("last_updated")
    val lastUpdated: Int,
    @SerializedName("market_cap_ath_date")
    val marketCapAthDate: String,
    @SerializedName("market_cap_ath_value")
    val marketCapAthValue: Long,
    @SerializedName("market_cap_change_24h")
    val dailyMarketCapChange: Double,
    @SerializedName("market_cap_usd")
    val marketCapUsd: Long,
    @SerializedName("volume_24h_ath_date")
    val volumeAthDate: String,
    @SerializedName("volume_24h_ath_value")
    val volumeAthValue: Long,
    @SerializedName("volume_24h_change_24h")
    val dailyVolumeChange: Double,
    @SerializedName("volume_24h_percent_from_ath")
    val DailyVolumeFromAth: Double,
    @SerializedName("volume_24h_percent_to_ath")
    val DailyVolumeToAth: Double,
    @SerializedName("volume_24h_usd")
    val dailyVolumeUsd: Long
)


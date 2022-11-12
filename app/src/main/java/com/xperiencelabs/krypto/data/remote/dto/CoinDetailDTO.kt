package com.xperiencelabs.krypto.data.remote.dto

import com.xperiencelabs.krypto.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName
data class CoinDetailDTO(
    val description: String,
    val development_status: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: String,

    @SerializedName("links_extended")

    val logo: String,
    val message: String,
    val name: String,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: String,
    @SerializedName("proof_type")
    val proof_type: String,
    val rank: Int,
    val started_at: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<Creators>,
    val type: String,
    val whitepaper: Whitepaper
)
//mapping api returned data to data which will be passing to UI
fun CoinDetailDTO.toCoinDetail():CoinDetail{
    return  CoinDetail(
     coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        team = team,
        tags = tags.map {
            it.name },
        logo = logo
    )
}
package com.xperiencelabs.krypto.data.remote.dto
//DTO stands for data transfer objects
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.xperiencelabs.krypto.domain.model.Coin


data class CoinDTO(
    val id: String,
    //SerializedName annotation is used  to different name instead of actual name
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val rank: Int,
    val symbol: String,
    val type: String
)
//to map it to Coin data class
fun CoinDTO.toCoin(): Coin {
    return Coin(
        id=id,
        isActive= isActive,
        name = name,
        rank= rank,
        symbol= symbol
    )
}

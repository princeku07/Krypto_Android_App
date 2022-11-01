package com.xperiencelabs.krypto.domain.repository

import com.xperiencelabs.krypto.data.remote.dto.CoinDTO
import com.xperiencelabs.krypto.data.remote.dto.CoinDetailDTO

interface CoinRepository {

    suspend fun getCoins():List<CoinDTO>
    suspend fun getCoinsById(coinId:String): CoinDetailDTO
}
package com.xperiencelabs.krypto.data.repository

import com.xperiencelabs.krypto.data.remote.CurrencyAPI
import com.xperiencelabs.krypto.data.remote.dto.CoinDTO
import com.xperiencelabs.krypto.data.remote.dto.CoinDetailDTO
import com.xperiencelabs.krypto.data.remote.dto.TickerEntity
import com.xperiencelabs.krypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api:CurrencyAPI
):CoinRepository {
    override suspend fun getCoins(): List<CoinDTO> {
        return api.getCoins()
    }

    override suspend fun getCoinsById(coinId:String): CoinDetailDTO {
        return api.getCoinById(coinId)
    }

    override suspend fun getTickersById(coinId: String): TickerEntity {
        return api.getTickersById(coinId)
    }
}
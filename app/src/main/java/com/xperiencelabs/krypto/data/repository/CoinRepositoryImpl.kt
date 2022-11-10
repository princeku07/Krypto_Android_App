package com.xperiencelabs.krypto.data.repository

import com.xperiencelabs.krypto.data.remote.CurrencyAPI
import com.xperiencelabs.krypto.data.remote.dto.*
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

    override suspend fun getEvents(coinId: String): List<EventsEntity> {
        return api.getEvents(coinId)
    }
}
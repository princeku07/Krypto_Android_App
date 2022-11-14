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

    override suspend fun getTickers(): List<TickerEntity> {
        return api.getTickers()
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

    override suspend fun getLatestNews(): List<NewsEntity> {
        println("news")
        return api.getLatestNews(10)

    }
}
package com.xperiencelabs.krypto.domain.repository

import com.xperiencelabs.krypto.data.remote.dto.*

interface CoinRepository {

    suspend fun getCoins():List<CoinDTO>
    suspend fun getTickers():List<TickerEntity>
    suspend fun getCoinsById(coinId:String): CoinDetailDTO
    suspend fun getTickersById(coinId: String):TickerEntity
    suspend fun getEvents(coinId: String):List<EventsEntity>
    suspend fun getLatestNews():List<NewsEntity>
}
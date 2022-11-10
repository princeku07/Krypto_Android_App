package com.xperiencelabs.krypto.domain.repository

import com.xperiencelabs.krypto.data.remote.dto.CoinDTO
import com.xperiencelabs.krypto.data.remote.dto.CoinDetailDTO
import com.xperiencelabs.krypto.data.remote.dto.EventsEntity
import com.xperiencelabs.krypto.data.remote.dto.TickerEntity

interface CoinRepository {

    suspend fun getCoins():List<CoinDTO>
    suspend fun getCoinsById(coinId:String): CoinDetailDTO
    suspend fun getTickersById(coinId: String):TickerEntity
    suspend fun getEvents(coinId: String):List<EventsEntity>
}
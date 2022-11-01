package com.xperiencelabs.krypto.data.remote

import com.xperiencelabs.krypto.data.remote.dto.CoinDTO
import com.xperiencelabs.krypto.data.remote.dto.CoinDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path


//Getting Data from CoinPaprikaAPI
interface CurrencyAPI {

        @GET("/v1/coins")
        suspend fun getCoins():List<CoinDTO>

        @GET("/v1/coins/{coinId}")
        suspend fun getCoinById(@Path("coinId") coinId:String) : CoinDetailDTO


}
package com.xperiencelabs.krypto.data.remote

import com.xperiencelabs.krypto.data.remote.dto.CoinDTO
import com.xperiencelabs.krypto.data.remote.dto.CoinDetailDTO
import com.xperiencelabs.krypto.data.remote.dto.NewsService
import com.xperiencelabs.krypto.data.remote.dto.TickerEntity
import com.xperiencelabs.krypto.domain.model.GlobalStats
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//Getting Data from CoinPaprikaAPI
interface CurrencyAPI {

        //get list of coins
        @GET("coins")
        suspend fun getCoins():List<CoinDTO>

        //get coin by Id
        @GET("coins/{coinId}")
        suspend fun getCoinById(@Path("coinId") coinId:String) : CoinDetailDTO

        //get tickers
        @GET("tickers/{coinId}")
        suspend fun getTickersById(@Path("coinId") coinId: String) : TickerEntity

        //get global Stats of CryptoCurrency
        @GET("global")
        suspend fun getGlobalStats():GlobalStats

        //Getting latest news
        @GET("news/latest")
        suspend fun getNews(@Query("limit") limit:Int) :List<NewsService>
        //





}
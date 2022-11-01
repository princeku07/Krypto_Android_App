package com.xperiencelabs.krypto.domain.model

import com.xperiencelabs.krypto.data.remote.dto.Creators

data class CoinDetail(
    val coinId:String,
    val name:String,
    val description:String,
    val symbol:String,
    val rank:Int,
    val isActive:Boolean,
    val team:List<Creators>,
    val tags:List<String>,
    val logo:String

)

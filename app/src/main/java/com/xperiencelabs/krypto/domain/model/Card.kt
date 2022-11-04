package com.xperiencelabs.krypto.domain.model

import com.xperiencelabs.krypto.data.remote.dto.Quotes


data class Card(
    val id:String,
    val logo:String,
    val currName:String,
    val symbol:String,
    val totalSupply:Long,
    val currPrice:Double,
    val changeHour:Double,
    val changeDaily:Double,
    val changeWeekly:Double,
    val quotes: Quotes,
)

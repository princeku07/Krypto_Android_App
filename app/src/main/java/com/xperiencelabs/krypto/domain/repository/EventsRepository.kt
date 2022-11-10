package com.xperiencelabs.krypto.domain.repository

interface EventsRepository {
    suspend fun getEventsById(coinId:String){

    }
}
package com.xperiencelabs.krypto.presenter.events

import com.xperiencelabs.krypto.data.remote.dto.EventsEntity

data class EventsState (
    val isLoading:Boolean = false,
    val events: List<EventsEntity>  = emptyList(),
    val error:String=" "
        )
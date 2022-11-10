package com.xperiencelabs.krypto.presenter.events

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xperiencelabs.krypto.domain.use_case.events.GetEventsUseCase
import com.xperiencelabs.krypto.presenter.currency_detail.CoinDetailState
import com.xperiencelabs.krypto.utils.Constants
import com.xperiencelabs.krypto.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class EventsViewModel  @Inject constructor(
    val eventsUseCase: GetEventsUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _state = mutableStateOf(EventsState())
    val state:State<EventsState> = _state

//    //get coin id when click in list view in home page
    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getEvents(coinId)
        }
    }

    private fun getEvents(coinId:String){
        eventsUseCase(coinId).onEach { event->
            when(event){
                is Response.Success -> {
                    _state.value = EventsState(events= event.data?: emptyList())
                }
                is Response.Error ->{
                    _state.value = EventsState(error = event.message ?: "Error" )
                }
                is Response.Loading -> {
                    _state.value = EventsState(isLoading = true)
                }
            }


        }.launchIn(viewModelScope)

    }

}
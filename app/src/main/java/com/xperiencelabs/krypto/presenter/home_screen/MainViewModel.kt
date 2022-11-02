package com.xperiencelabs.krypto.presenter.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xperiencelabs.krypto.domain.use_case.get_currencies.GetCoinsUseCase
import com.xperiencelabs.krypto.domain.use_case.get_currency.GetCoinUseCase
import com.xperiencelabs.krypto.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//A Hilt View Model is a jetpack viewModel that is constructor injected by Hilt. To enable injection of a ViewModel by Hilt
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) :ViewModel(){

    private val _state = mutableStateOf(CoinListState())
    val state:State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result){
                is Response.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Response.Error ->{
                    _state.value = CoinListState(error = result.message ?: "Error" )
                }
                is Response.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.xperiencelabs.krypto.presenter.currency_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xperiencelabs.krypto.domain.use_case.get_currency.GetCoinUseCase
import com.xperiencelabs.krypto.utils.Constants
import com.xperiencelabs.krypto.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//A Hilt View Model is a jetpack viewModel that is constructor injected by Hilt. To enable injection of a ViewModel by Hilt
//A handle to saved state passed down to androidx.lifecycle.ViewModel.
// You should use SavedStateViewModelFactory if you want to receive this object in ViewModel's constructor.
// "https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle"
@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
     savedStateHandle: SavedStateHandle
) :ViewModel(){

    private val _state = mutableStateOf(CoinDetailState())
    val state:State<CoinDetailState> = _state

    //get coin id when click in list view in home page
    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId:String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Response.Success -> {
                    _state.value = CoinDetailState(coin = result.data )
                }
                is Response.Error ->{
                    _state.value = CoinDetailState(error = result.message ?: "Error" )
                }
                is Response.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.xperiencelabs.krypto.presenter.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xperiencelabs.krypto.domain.use_case.news.LatestNews
import com.xperiencelabs.krypto.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val latestNews: LatestNews
):ViewModel() {

    private val _state = mutableStateOf(NewsListState())
   val state:State<NewsListState> = _state

init {
    getNews()
}
    private fun getNews(){
        latestNews().onEach { result ->
            when(result){
                is Response.Success -> {

                    _state.value = NewsListState(news = result.data ?: emptyList())
                }
                is Response.Error ->{
                    _state.value = NewsListState(error = result.message ?: "Error" )
                }
                is Response.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}
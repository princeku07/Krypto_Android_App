package com.xperiencelabs.krypto.domain.use_case.get_currencies

import com.xperiencelabs.krypto.data.remote.dto.toCoin
import com.xperiencelabs.krypto.domain.model.Coin
import com.xperiencelabs.krypto.domain.repository.CoinRepository
import com.xperiencelabs.krypto.utils.Response
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository:CoinRepository
){
    /*  Using Coroutine flow
    We use invoke when we don't want specify function name.
    Flow can emit multiple values sequentially (used to receive live updates from a database).It is a stream of data
    that can be computed asynchronously.(Resource = https://developer.android.com/kotlin/flow)
     */
    operator fun invoke(): Flow<Response<List<Coin>>> = flow {
        try {
                emit(Response.Loading())
            val coins = repository.getCoins().map {
                it.toCoin() }

            emit(Response.Success(coins.slice(0..10)))
        }
        catch (e:HttpException){
                emit(Response.Error(e.localizedMessage ?: "error" ))
        }
        catch (e:IOException){
                 emit(Response.Error("Check internet connection"))
        }
    }
}
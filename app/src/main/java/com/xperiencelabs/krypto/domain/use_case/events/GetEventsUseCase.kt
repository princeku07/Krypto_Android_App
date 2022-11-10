package com.xperiencelabs.krypto.domain.use_case.events

import com.xperiencelabs.krypto.data.remote.dto.EventsEntity
import com.xperiencelabs.krypto.domain.repository.CoinRepository
import com.xperiencelabs.krypto.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
   private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId:String):Flow<Response<List<EventsEntity>>> = flow{
        try {
            emit(Response.Loading())
            val events = coinRepository.getEvents(coinId)
            emit(Response.Success(events))
        }
        catch (e:HttpException){
            emit(Response.Error(e.localizedMessage ?: "error" ))
        }
        catch (e: IOException){
            emit(Response.Error("Check internet connection"))
        }
    }
}
package com.xperiencelabs.krypto.domain.use_case.news

import com.xperiencelabs.krypto.data.remote.dto.NewsEntity
import com.xperiencelabs.krypto.domain.repository.CoinRepository
import com.xperiencelabs.krypto.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LatestNews @Inject constructor(
   private val repository: CoinRepository
) {
    operator fun invoke():Flow<Response<List<NewsEntity>>> = flow {
        try {
             emit(Response.Loading())
            val news = repository.getLatestNews()

            emit(Response.Success(news))
        }
        catch (e: HttpException){
            emit(Response.Error(e.localizedMessage ?: "error" ))
        }
        catch (e: IOException){
            emit(Response.Error("Check internet connection"))
        }
    }
}
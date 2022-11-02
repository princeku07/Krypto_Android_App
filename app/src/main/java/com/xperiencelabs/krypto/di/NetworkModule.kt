package com.xperiencelabs.krypto.di

import com.xperiencelabs.krypto.data.remote.CurrencyAPI
import com.xperiencelabs.krypto.data.repository.CoinRepositoryImpl
import com.xperiencelabs.krypto.domain.repository.CoinRepository
import com.xperiencelabs.krypto.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//Indicates in which Hilt-generated DI containers module bindings must be available.
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCurrencyApi():CurrencyAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyAPI::class.java)
    }
    @Provides
    @Singleton
    fun provideCoinRepository(api:CurrencyAPI):CoinRepository{
        return  CoinRepositoryImpl(api)
    }

}
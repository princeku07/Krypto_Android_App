package com.xperiencelabs.krypto.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsEntity(
    val title: String,
    val lead: String,
    val url: String,
    @SerializedName("news_date")
    val newsDate: String
)

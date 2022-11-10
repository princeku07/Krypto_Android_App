package com.xperiencelabs.krypto.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun LottieAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://assets7.lottiefiles.com/packages/lf20_oo9n3jl8.json"))
    com.airbnb.lottie.compose.LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}
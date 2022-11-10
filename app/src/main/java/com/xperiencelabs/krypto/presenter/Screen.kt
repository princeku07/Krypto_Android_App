package com.xperiencelabs.krypto.presenter

sealed class Screen(val route:String){
    object HomeScreen:Screen("home_screen")
    object CoinListScreen:Screen("coin_list_screen")
    object CoinDetail:Screen("coin_detail")
    object SplashScreen:Screen("splash_screen")
}

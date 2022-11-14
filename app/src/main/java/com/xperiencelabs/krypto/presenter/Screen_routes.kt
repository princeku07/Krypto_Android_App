package com.xperiencelabs.krypto.presenter

sealed class Screen_routes(val route:String){
    object HomeScreen:Screen_routes("home_screen")
    object TopTen:Screen_routes("top_ten")
    object EventsScreen:Screen_routes("event_screen")
    object CoinDetail:Screen_routes("coin_detail")
    object SplashScreen:Screen_routes("splash_screen")
    object Info:Screen_routes("info")
}


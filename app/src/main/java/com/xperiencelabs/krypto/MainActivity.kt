package com.xperiencelabs.krypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xperiencelabs.krypto.presenter.Screen_routes
import com.xperiencelabs.krypto.presenter.SplashScreen
import com.xperiencelabs.krypto.presenter.currency_detail.CoinDetail
import com.xperiencelabs.krypto.presenter.events.components.EventScreen
import com.xperiencelabs.krypto.presenter.home_screen.HomeScreen
import com.xperiencelabs.krypto.presenter.home_screen.components.TopTenCoins
import com.xperiencelabs.krypto.presenter.theme.*
import dagger.hilt.android.AndroidEntryPoint
//used to inject Dagger-Hilt dependencies
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KryptoTheme {
                // A surface container using the 'background' color from the theme

                Surface(

                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen_routes.SplashScreen.route)
                    {
                        composable(route=Screen_routes.SplashScreen.route){
                            SplashScreen(navController = navController)
                        }
                        //homeScreen
                        composable(route = Screen_routes.HomeScreen.route){
                            HomeScreen(navController)
                        }
                        //CoinDetail
                        composable(route = Screen_routes.CoinDetail.route + "/{coinId}"){
                            CoinDetail(navController)
                        }
                        //CoinEvents
                        composable(route = Screen_routes.EventsScreen.route +"/{coinId}"){
                            EventScreen()
                        }
                        //Top ten
                        composable(route= Screen_routes.TopTen.route){
                            TopTenCoins(navController = navController)
                        }
                        //Info
                        composable(route = Screen_routes.Info.route){
                            Info()
                        }
                    }
                }
            }
        }
    }
}


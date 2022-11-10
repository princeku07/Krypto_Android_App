package com.xperiencelabs.krypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xperiencelabs.krypto.presenter.Screen
import com.xperiencelabs.krypto.presenter.SplashScreen
import com.xperiencelabs.krypto.presenter.currency_detail.CoinDetail
import com.xperiencelabs.krypto.presenter.home_screen.CoinListScreen
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
                    NavHost(navController = navController, startDestination = Screen.SplashScreen.route)
                    {
                        composable(route=Screen.SplashScreen.route){
                            SplashScreen(navController = navController)
                        }
                        //homeScreen
                        composable(route = Screen.HomeScreen.route){
                            HomeScreen(navController)
                        }
                        //CoinDetail
                        composable(route = Screen.CoinDetail.route + "/{coinId}"){
                            CoinDetail()
                        }
                    }
                }
            }
        }
    }
}


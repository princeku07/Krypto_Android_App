package com.xperiencelabs.krypto.presenter.home_screen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.data.remote.dto.TickerEntity
import com.xperiencelabs.krypto.presenter.LottieAnimation
import com.xperiencelabs.krypto.presenter.Screen_routes
import com.xperiencelabs.krypto.presenter.home_screen.BottomBar
import com.xperiencelabs.krypto.presenter.home_screen.MainViewModel
import com.xperiencelabs.krypto.presenter.home_screen.standardQuadFromTO
import com.xperiencelabs.krypto.presenter.theme.*
import com.xperiencelabs.krypto.utils.BottomNavItem
import kotlin.math.roundToInt


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopTenCoins(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val  state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState=scaffoldState,
        bottomBar = {
            BottomBar(items = listOf(
                BottomNavItem(
                    name = "Home",
                    route = Screen_routes.HomeScreen.route,
                    icon = Icons.Default.Home
                ),
                BottomNavItem(
                    name = "Top",
                    route = Screen_routes.TopTen.route,
                    icon = Icons.Default.Star
                ),

                BottomNavItem(
                    name = "Info",
                    route = Screen_routes.Info.route,
                    icon = Icons.Default.Info
                )
            ), navController = navController , onItemClick ={
                navController.navigate(it.route)
            } )
        }


    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Text(text = "Popular", style = MaterialTheme.typography.h6, color = Color.White, modifier = Modifier
                    .padding(15.dp)
                )


                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
                ){
                    items(state.coins){ coin->
                        CoinCard(coin = coin,
                            onItemClick = {
                                navController.navigate(Screen_routes.CoinDetail.route + "/${coin.id}")
                            })

                    }
                }
            }

            if(state.error.isNotBlank()){
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if(state.isLoading){
                LottieAnimation()
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}

@Composable
fun CoinCard(
    onItemClick: (TickerEntity) -> Unit,
    coin: TickerEntity
) {
    val imageId = "${ coin.symbol.lowercase()}-${coin.name.lowercase().replace(" ","-")}"
    val gradientColor = Brush.verticalGradient(listOf(cardGradient1, cardGradient2))
    Column(
        modifier = Modifier
            .fillMaxWidth()

            .padding(10.dp)
            .clickable { onItemClick(coin) }
            .clip(RoundedCornerShape(20.dp))
            .border(
                1.dp, gradientColor, shape = RoundedCornerShape(20.dp)
            )
            .background(gradient2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            val link = "https://static.coinpaprika.com/coin/"+"$imageId"+"/logo.png"
            println(link)
            GlideImage(
                imageModel = { link },
                modifier = Modifier.size(35.dp)
            )
//
            Text(
                text = buildAnnotatedString {
                    val symbolStyle = SpanStyle(
                        gold, fontWeight = FontWeight.SemiBold, fontSize = 10.sp
                    )
                    append("${coin.name} ")
                    pushStyle(symbolStyle)
                    append(coin.symbol)
                },
                style = MaterialTheme.typography.body1,

                modifier = Modifier.padding(start = 10.dp)

                )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            val price:String = ("%.2f".format(coin.quotes.USD.price))
            Text(
                text = buildAnnotatedString {
                    val symbolStyle = SpanStyle(
                         fontWeight = FontWeight.SemiBold, color = Color.White
                    )
                    append("$")
                    pushStyle(symbolStyle)
                    append(price)
                },
                style = MaterialTheme.typography.h5,
                color= gold,
                modifier = Modifier.padding(start = 10.dp)

            )

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column {
                val marketCap:String = (coin.quotes.USD.market_cap/1000000000).toString()
                Text(text = "Market Cap", fontSize = 10.sp, color = Color.LightGray)
                Text(text = "${marketCap}B", style = MaterialTheme.typography.body1, color = Color.White)
            }
            Text(text = "Active", style = MaterialTheme.typography.body1, color = Color.Green)
        }
    }
}

@Composable
fun CurrencyGrid(
    navController:NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Row(){
        state.coins.forEach{coin->
            if (coin.rank == 1){
                SelectedCurrency(coin = coin,
                    onItemClick = {
                        navController.navigate(Screen_routes.CoinDetail.route + "/${coin.id}")
                    }
                )
            }
        }
        }
    }


@Composable
fun SelectedCurrency(
    onItemClick: (TickerEntity) -> Unit,
    coin: TickerEntity
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onItemClick(coin) }
    ) {
        val imageId = "${ coin.symbol.lowercase()}-${coin.name.lowercase()}"
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, end = 25.dp)
        ) {
            Text(
                text = "Your Coin",
                fontSize = 10.sp,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.align(Alignment.Bottom)
            )
            Icon(
                painter = painterResource(id = R.drawable.stats),
                contentDescription = "Globe",
                modifier = Modifier.size(30.dp), tint = gold
            )
        }
        BoxWithConstraints(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .aspectRatio(1.75f, matchHeightConstraintsFirst = true)
                .border(
                    1.dp, gold, shape = RoundedCornerShape(30.dp)
                )
                .clip(RoundedCornerShape(30.dp))
                .background(card)


        ) {
            val width = constraints.maxWidth
            val height = constraints.maxHeight
            //medium colored path

            val mediumColoredPoint1 = Offset(0f, height * 0.3f)
            val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.1f)
            val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.5f)
            val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
            val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())
            //medium Colored Path
            val mediumColoredPath = Path().apply {
                moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
                standardQuadFromTO(mediumColoredPoint1, mediumColoredPoint2)
                standardQuadFromTO(mediumColoredPoint2, mediumColoredPoint3)
                standardQuadFromTO(mediumColoredPoint3, mediumColoredPoint4)
                standardQuadFromTO(mediumColoredPoint4, mediumColoredPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }
            //light colored path
            val lightColoredPoint1 = Offset(0f,height*0.65f)
            val lightColoredPoint2 = Offset(width*0.1f,height*0.4f)
            val lightColoredPoint3 = Offset(width*0.3f,height*0.35f)
            val lightColoredPoint4 = Offset(width*0.65f,height.toFloat())
            val lightColoredPoint5 = Offset(width*1.7f,-height.toFloat() /3f)

            val lightColoredPath = Path().apply {
                moveTo(lightColoredPoint1.x,lightColoredPoint1.y)
                standardQuadFromTO(lightColoredPoint1,lightColoredPoint2)
                standardQuadFromTO(lightColoredPoint2,lightColoredPoint3)
                standardQuadFromTO(lightColoredPoint3,lightColoredPoint4)
                standardQuadFromTO(lightColoredPoint4,lightColoredPoint5)
                lineTo(width.toFloat() + 100f,height.toFloat() +100f)
                lineTo(-100f,height.toFloat() +100f)
                close()

            }
            Canvas (modifier = Modifier.fillMaxSize()){
                drawPath(
                    path = mediumColoredPath,
                    color = card1
                )
                drawPath(
                    path = lightColoredPath,
                    color = card2
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ){
                Column(modifier =Modifier.fillMaxWidth()
                ) {
                    Row(horizontalArrangement= Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth())
                    {
                        val link = "https://static.coinpaprika.com/coin/"+"$imageId"+"/logo.png"

                        GlideImage(imageModel = {
                             link

                        },
                            modifier = Modifier.size(60.dp)
                        )

                        Column {
                            val price:String = ("%.2f".format(coin.quotes.USD.price))

                            Text(
                                text = buildAnnotatedString {
                                    val symbolStyle = SpanStyle(
                                        gold, fontWeight = FontWeight.SemiBold, fontSize = 15.sp
                                    )
                                    append( "${coin.name} " )
                                    pushStyle(symbolStyle)
                                    append(coin.symbol)
                                },
                                style = MaterialTheme.typography.h5,

                                )
                            Text(
                                text = price,
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                            )
                        }
                        Column {
                            Text(
                                text = "Total Supply",
                                fontSize = 10.sp,
                                color = Color.LightGray,
                                modifier = Modifier
                            )
                            Text(
                                text = coin.total_supply.toString(),
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                    ) {

                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "1h",
                                color = Color.White,
                                style = MaterialTheme.typography.h6
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row {
                                val increased:Boolean = coin.quotes.USD.percent_change_1h < 0
                                Text(text = "${ coin.quotes.USD.percent_change_1h}%", style = MaterialTheme.typography.body1,
                                    color = if(increased) Color.Red else Color.Green
                                )
                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                    contentDescription = null,
                                    tint = if(increased) Color.Red else Color.Green
                                )

                              }
                        }
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "24 h",
                                color = Color.White,
                                style = MaterialTheme.typography.h6
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row {
                                val increased:Boolean = coin.quotes.USD.percent_change_24h < 0
                                Text(text = "${ coin.quotes.USD.percent_change_24h}%", style = MaterialTheme.typography.body1,
                                    color = if(increased) Color.Red else Color.Green
                                )
                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                    contentDescription = null,
                                    tint = if(increased) Color.Red else Color.Green
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "7 days",
                                color = Color.White,
                                style = MaterialTheme.typography.h6
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row {
                                val increased:Boolean = coin.quotes.USD.percent_change_7d < 0
                                Text(text = "${ coin.quotes.USD.percent_change_7d}%", style = MaterialTheme.typography.body1,
                                    color = if(increased) Color.Red else Color.Green
                                )
                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                    contentDescription = null,
                                    tint = if(increased) Color.Red else Color.Green
                                )
                            }
                        }
                    }

                }




            }
        }
    }
}

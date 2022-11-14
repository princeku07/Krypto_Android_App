package com.xperiencelabs.krypto.presenter.currency_detail

import android.annotation.SuppressLint
import com.skydoves.landscapist.glide.GlideImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.presenter.LottieAnimation
import com.xperiencelabs.krypto.presenter.Screen_routes
import com.xperiencelabs.krypto.presenter.currency_detail.components.ChangeTab
import com.xperiencelabs.krypto.presenter.currency_detail.components.Tabs
import com.xperiencelabs.krypto.presenter.theme.*
import com.xperiencelabs.krypto.utils.TopBarCollapsedHeight
import com.xperiencelabs.krypto.utils.TopBarExpandedHeight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CoinDetail(
    navController: NavController,
    viewModel: CurrencyDetailViewModel = hiltViewModel()

    ) {
    val state = viewModel.state.value
    val gradientColor = Brush.verticalGradient(listOf(gradient1, gradient2))
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Column(

        ) {
            Box(modifier = Modifier.fillMaxSize()){
                state.coin?.let { tickers ->
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .background(gradientColor)){
                        item {
                            TopAppBar(
                                contentPadding = PaddingValues(), backgroundColor = card, modifier = Modifier
                                    .height(
                                        TopBarExpandedHeight
                                    )
                                    .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                            ) {

                                Column {
                                    val imageId = "${ tickers.symbol.lowercase()}-${tickers.name.lowercase().replace(oldValue = " ", newValue = "-")}"
                                    Box {
                                        Box(Modifier.height(TopBarExpandedHeight - TopBarCollapsedHeight)) {
                                            val imageLink =
                                                "https://static.coinpaprika.com/coin/$imageId/logo.png"
                                            GlideImage(imageModel = { imageLink }, modifier = Modifier
                                                .size(150.dp)
                                                .align(
                                                    Alignment.Center
                                                ))
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(
                                                        Brush.verticalGradient(
                                                            colorStops = arrayOf(
                                                                Pair(0.4f, Color(0x00FFFFFF)),
                                                                Pair(1f, DetailGradient2)
                                                            )
                                                        )
                                                    )
                                            )
                                            Row(
                                                verticalAlignment = Alignment.Bottom,
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .padding(horizontal = 20.dp),

                                                )
                                            {
                                                val price: String =
                                                    ("%.4f".format(tickers.quotes.USD.price))
                                                Text(
                                                    text = buildAnnotatedString {
                                                        val symbolStyle = SpanStyle(
                                                            Color.White,
                                                            fontWeight = FontWeight.SemiBold,
                                                            fontSize = 20.sp
                                                        )
                                                        append("$ ")
                                                        pushStyle(symbolStyle)
                                                        append(price)
                                                    },
                                                    color = gold,
                                                    style = MaterialTheme.typography.h5,
                                                    modifier = Modifier
                                                        .clip(Shapes.small)
                                                        .background(Color.Black)
                                                        .padding(
                                                            vertical = 6.dp,
                                                            horizontal = 16.dp
                                                        )
                                                )
                                                Text(text = "Rank - ${ tickers.rank.toString() }", color=Color.Green,modifier= Modifier.padding(5.dp))


                                            }

                                        }
                                    }
                                    Column(
                                        Modifier
                                            .fillMaxWidth()
                                            .background(DetailGradient2)
                                            .height(TopBarCollapsedHeight),
                                        verticalArrangement = Arrangement.Center
                                    )
                                    {
                                        Text(
                                            text = buildAnnotatedString {
                                                val symbolStyle = SpanStyle(
                                                    gold, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
                                                )
                                                append(tickers.name+" ")
                                                pushStyle(symbolStyle)
                                                append(tickers.symbol)
                                            },
                                            style = MaterialTheme.typography.h4,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 20.dp)
                                        )
                                    }


                                }

                            }

                            //-----------------------------------------------------//--------------------------------------//
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Button(modifier = Modifier.padding(end = 10.dp),
                                    onClick = {
                                        navController.navigate(Screen_routes.EventsScreen.route + "/${tickers.id}")
                                        },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = gradient1)
                                ) {
                                    Text(text = "Events",
                                        style = MaterialTheme.typography.h6,
                                        color = Color.White

                                        )
                                }
                                Button(
                                    onClick = {

                                    }, colors = ButtonDefaults.buttonColors(backgroundColor = gradient1)
                                ) {
                                    Text(text = "Markets", style = MaterialTheme.typography.h6, color = Color.White)
                                }
                            }
                            Spacer(modifier = Modifier.height(7.dp))
                            var selectedTabIndex by remember {
                                mutableStateOf(0)
                            }
                            ChangeTab(
                                tabs = listOf(
                                    Tabs("Price"),
                                    Tabs("Volume")

                                )
                            ){
                                selectedTabIndex = it
                            }

                            val boxGradient = Brush.linearGradient(listOf(Box1,Box2,Box3))
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier

                                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(boxGradient)

                                    .height(300.dp)
                                    .width(500.dp)
                            ) {
                                   Row(
                                       horizontalArrangement = Arrangement.SpaceEvenly
                                   ) {
                                       when(selectedTabIndex){

                                           0 -> Column(
                                               modifier = Modifier.padding(30.dp)
                                           ) {
                                               Row {
                                                   Column {
                                                       Row(
                                                           verticalAlignment = Alignment.CenterVertically,
                                                           horizontalArrangement = Arrangement.SpaceBetween,
                                                           modifier = Modifier.padding(8.dp)
                                                       ) {
                                                           val increased:Boolean = tickers.quotes.USD.percent_change_15m < 0
                                                           Text(text = "15m -" , modifier = Modifier.padding(end = 25.dp))
                                                           Text(text = "${ tickers.quotes.USD.percent_change_15m}%", style = MaterialTheme.typography.body1,
                                                               color = if(increased) Color.Red else Color.Green
                                                           )
                                                           Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                               contentDescription = null,
                                                               tint = if(increased) Color.Red else Color.Green
                                                           )

                                                       }
                                                       Row(
                                                           verticalAlignment = Alignment.CenterVertically,
                                                           horizontalArrangement = Arrangement.SpaceBetween,
                                                           modifier = Modifier.padding(8.dp)
                                                       ) {
                                                           val increased:Boolean = tickers.quotes.USD.percent_change_30m < 0
                                                           Text(text = "30m -" , modifier = Modifier.padding(end = 25.dp))
                                                           Text(text = "${ tickers.quotes.USD.percent_change_30m}%", style = MaterialTheme.typography.body1,
                                                               color = if(increased) Color.Red else Color.Green
                                                           )
                                                           Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                               contentDescription = null,
                                                               tint = if(increased) Color.Red else Color.Green
                                                           )

                                                       }
                                                       Row(
                                                           verticalAlignment = Alignment.CenterVertically,
                                                           horizontalArrangement = Arrangement.SpaceBetween,
                                                           modifier = Modifier.padding(8.dp)
                                                       ) {
                                                           val increased:Boolean = tickers.quotes.USD.percent_change_6h < 0
                                                           Text(text = "1h -" , modifier = Modifier.padding(end = 25.dp))
                                                           Text(text = "${ tickers.quotes.USD.percent_change_6h}%", style = MaterialTheme.typography.body1,
                                                               color = if(increased) Color.Red else Color.Green
                                                           )
                                                           Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                               contentDescription = null,
                                                               tint = if(increased) Color.Red else Color.Green
                                                           )

                                                       }
                                                   }
                                                   Divider(color =Color(0xFF646464), modifier = Modifier
                                                       .fillMaxHeight()
                                                       .width(1.dp))
                                                   Column {
                                                       Row(
                                                           verticalAlignment = Alignment.CenterVertically,
                                                           horizontalArrangement = Arrangement.SpaceBetween,
                                                           modifier = Modifier.padding(8.dp)
                                                       ) {
                                                           val increased:Boolean = tickers.quotes.USD.percent_change_24h < 0
                                                           Text(text = "15m -" , modifier = Modifier.padding(end = 25.dp))
                                                           Text(text = "${ tickers.quotes.USD.percent_change_24h}%", style = MaterialTheme.typography.body1,
                                                               color = if(increased) Color.Red else Color.Green
                                                           )
                                                           Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                               contentDescription = null,
                                                               tint = if(increased) Color.Red else Color.Green
                                                           )

                                                       }
                                                       Row(
                                                           verticalAlignment = Alignment.CenterVertically,
                                                           horizontalArrangement = Arrangement.SpaceBetween,
                                                           modifier = Modifier.padding(8.dp)
                                                       ) {
                                                           val increased:Boolean = tickers.quotes.USD.percent_change_7d < 0
                                                           Text(text = "30m -" , modifier = Modifier.padding(end = 25.dp))
                                                           Text(text = "${ tickers.quotes.USD.percent_change_7d}%", style = MaterialTheme.typography.body1,
                                                               color = if(increased) Color.Red else Color.Green
                                                           )
                                                           Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                               contentDescription = null,
                                                               tint = if(increased) Color.Red else Color.Green
                                                           )

                                                       }
                                                       Row(
                                                           verticalAlignment = Alignment.CenterVertically,
                                                           horizontalArrangement = Arrangement.SpaceBetween,
                                                           modifier = Modifier.padding(8.dp)
                                                       ) {
                                                           val increased:Boolean = tickers.quotes.USD.percent_change_1y < 0
                                                           Text(text = "1h -" , modifier = Modifier.padding(end = 25.dp))
                                                           Text(text = "${ tickers.quotes.USD.percent_change_1y}%", style = MaterialTheme.typography.body1,
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
                                           
                                           
                                           1 -> Column(
                                               modifier = Modifier.padding(30.dp)
                                           ) {
                                               Row(
                                                   verticalAlignment = Alignment.CenterVertically
                                               ) {
                                                   Text(text = "1 day -", style = MaterialTheme.typography.h5, color = lightGrey,
                                                   modifier = Modifier.padding(8.dp))
                                                   Text(text = tickers.quotes.USD.volume_24h.toString(), style = MaterialTheme.typography.h6)

                                               }
                                               Row(
                                                   verticalAlignment = Alignment.CenterVertically,
                                                   modifier = Modifier.padding(8.dp)
                                               ) {
                                                   val increased:Boolean = tickers.quotes.USD.volume_24h_change_24h < 0
                                                   Text(text = "${ tickers.quotes.USD.volume_24h_change_24h}%", style = MaterialTheme.typography.body1,
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
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

        }
    }
}



package com.xperiencelabs.krypto.presenter.currency_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.presenter.LottieAnimation
import com.xperiencelabs.krypto.presenter.theme.*
import com.xperiencelabs.krypto.utils.TopBarCollapsedHeight
import com.xperiencelabs.krypto.utils.TopBarExpandedHeight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CoinDetail(
//    navController: NavController,
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
                                    Box(Modifier.height(TopBarExpandedHeight - TopBarCollapsedHeight)) {

                                        Image(painter = painterResource(id = R.drawable.coin_logo),
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                        Box(modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(
                                                    colorStops = arrayOf(
                                                        Pair(0.4f, Color(0x00FFFFFF)),
                                                        Pair(1f, DetailGradient2)
                                                    )
                                                )
                                            ))
                                        Row(
                                            verticalAlignment = Alignment.Bottom,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .padding(horizontal = 20.dp),

                                            )
                                        {
                                            val price:String = ("%.4f".format(tickers.quotes.USD.price))
                                            Text(
                                                text = buildAnnotatedString {
                                                    val symbolStyle = SpanStyle(
                                                        Color.White, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
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
                                                    .padding(vertical = 6.dp, horizontal = 16.dp)
                                            )


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
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = {

                                }, colors = ButtonDefaults.buttonColors(backgroundColor = gradient1)
                                ) {
                                    Text(text = "Events", style = MaterialTheme.typography.h6, color = Color.White)
                                }
                                Button(
                                    onClick = {

                                    }, colors = ButtonDefaults.buttonColors(backgroundColor = gradient1)
                                ) {
                                    Text(text = "Markets", style = MaterialTheme.typography.h6, color = Color.White)
                                }
                            }
                            Spacer(modifier = Modifier.height(7.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(gradient2)
                            ) {
                                        val volume:String = ("%.4f".format(tickers.quotes.USD.price))
                                        Column(verticalArrangement = Arrangement.Bottom,

                                            modifier = Modifier
                                                .padding(5.dp)

                                        ) {

                                            val increased:Boolean = tickers.quotes.USD.percent_change_1h < 0
                                            Text(text = "1h", style = MaterialTheme.typography.h6)
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                    contentDescription = null,
                                                    tint = if(increased) Color.Red else Color.Green
                                                )
                                                Text(text = tickers.quotes.USD.percent_change_1h.toString(), style = MaterialTheme.typography.body1,
                                                    color = if(increased) Color.Red else Color.Green
                                                )
                                            }

                                        }

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier

                                                .padding(5.dp)
                                        ) {
                                            val increased:Boolean = tickers.quotes.USD.percent_change_24h < 0
                                            Text(text = "1d", style = MaterialTheme.typography.h6)
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                    contentDescription = null,
                                                    tint = if(increased) Color.Red else Color.Green
                                                )
                                                Text(text = tickers.quotes.USD.percent_change_24h.toString(), style = MaterialTheme.typography.body1,
                                                    color = if(increased) Color.Red else Color.Green
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier

                                                .padding(5.dp)
                                        ) {
                                            val increased:Boolean = tickers.quotes.USD.percent_change_7d < 0
                                            Text(text = "1w", style = MaterialTheme.typography.h6)
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                    contentDescription = null,
                                                    tint = if(increased) Color.Red else Color.Green
                                                )
                                                Text(text = tickers.quotes.USD.percent_change_7d.toString(), style = MaterialTheme.typography.body1,
                                                    color = if(increased) Color.Red else Color.Green
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier

                                                .padding(5.dp)
                                        ) {
                                            val increased:Boolean = tickers.quotes.USD.percent_change_30d < 0
                                            Text(text = "1m", style = MaterialTheme.typography.h6)
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                    contentDescription = null,
                                                    tint = if(increased) Color.Red else Color.Green
                                                )
                                                Text(text = tickers.quotes.USD.percent_change_30d.toString(), style = MaterialTheme.typography.body1,
                                                    color = if(increased) Color.Red else Color.Green
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .padding(5.dp)
                                        ) {
                                            val increased:Boolean = tickers.quotes.USD.percent_change_1y < 0
                                            Text(text = "1y", style = MaterialTheme.typography.h6)
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                                    contentDescription = null,
                                                    tint = if(increased) Color.Red else Color.Green
                                                )
                                                Text(text = tickers.quotes.USD.percent_change_1y.toString(), style = MaterialTheme.typography.body1,
                                                    color = if(increased) Color.Red else Color.Green
                                                )
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

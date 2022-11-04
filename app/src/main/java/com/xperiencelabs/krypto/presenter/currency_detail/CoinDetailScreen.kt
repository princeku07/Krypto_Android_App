package com.xperiencelabs.krypto.presenter.currency_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.presenter.theme.*
import com.xperiencelabs.krypto.utils.TopBarCollapsedHeight
import com.xperiencelabs.krypto.utils.TopBarExpandedHeight


@Composable
fun CoinDetailScreen(
    viewModel: CurrencyDetailViewModel = hiltViewModel()

){
    val gradientColor = Brush.verticalGradient(listOf(gradient1, gradient2))
    val state = viewModel.state.value
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
                                                Pair(1f, gradient1)
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
                                    .background(gradient1)
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
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                                .height(TopBarCollapsedHeight)
                                .padding(horizontal = 20.dp)
                        ) {
                            NavigationButton(painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24),Color.White)
                            NavigationButton(painterResource(id = R.drawable.stats),Color.White)
                        }
                    }

                    //-----------------------------------------------------//--------------------------------------//

                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(0.dp)
                                .background(Color.Black)
                        ){
                         item {
                             val volume:String = ("%.4f".format(tickers.quotes.USD.price))
                             Column(verticalArrangement = Arrangement.Bottom,

                                 modifier = Modifier
                                     .background(gradient2)
                                     .padding(5.dp)

                             ) {

                                 val increased:Boolean = tickers.quotes.USD.volume_24h_change_24h < 0
                                 Text(text = "1h", style = MaterialTheme.typography.h6)
                                 Spacer(modifier = Modifier.height(12.dp))
                                 Row(
                                     horizontalArrangement = Arrangement.SpaceBetween
                                 ) {
                                     Icon(painter =  painterResource(id = if (increased) R.drawable.ic_baseline_arrow_downward_24 else R.drawable.ic_baseline_arrow_upward_24 ) ,
                                         contentDescription = null,
                                         tint = if(increased) Color.Red else Color.Green
                                         )
                                     Text(text = tickers.quotes.USD.percent_change_1y.toString(), style = MaterialTheme.typography.h6,
                                         color = if(increased) Color.Red else Color.Green
                                     )
                                 }

                             }

                             Column(
                                 modifier = Modifier
                                     .background(gradient2)
                                     .padding(5.dp)
                             ) {
                                 Text(text = "6h", style = MaterialTheme.typography.h6)
                             }
                             Column(
                                 modifier = Modifier
                                     .background(gradient2)
                                     .padding(5.dp)
                             ) {
                                 Text(text = "12h", style = MaterialTheme.typography.h6)
                             }
                             Column(
                                 modifier = Modifier
                                     .background(gradient2)
                                     .padding(5.dp)
                             ) {
                                 Text(text = "24h", style = MaterialTheme.typography.h6)
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
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}


@Composable
fun NavigationButton(
    icon: Painter,
    color:Color,
    elevation: ButtonElevation?= ButtonDefaults.elevation(),
    onClick: () -> Unit = {}
) {
    Button(onClick = onClick,
        contentPadding = PaddingValues(),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(backgroundColor = card1, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(40.dp)
            .height(38.dp)
    ) {
        Icon(painter = icon, contentDescription = null,tint= color)
    }
}


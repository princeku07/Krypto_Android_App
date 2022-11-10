package com.xperiencelabs.krypto.presenter.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.domain.model.*
import com.xperiencelabs.krypto.presenter.Screen
import com.xperiencelabs.krypto.presenter.home_screen.components.CoinCard
import com.xperiencelabs.krypto.presenter.home_screen.components.TopTenCoins
import com.xperiencelabs.krypto.presenter.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val gradientColor = Brush.verticalGradient(listOf(gradient1, gradient2))
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    Scaffold(scaffoldState=scaffoldState) {

        Column(modifier = Modifier.background(gradientColor).fillMaxSize()) {

//            UserGreetings(name = "Prince Kumar")
//            Spacer(modifier = Modifier.height(15.dp))
//            SelectedCurrency()
//            SearchBar()
//            LatestNews(listOf(
//                LatestNewsItems(
//                    "A record 55,000 Bitcoin, or over $1.1 billion, was just withdrawn from Binance",
//                    "Some serious withdrawals are occurring on the world’s biggest crypto exchange by volume",
//                    "01-01-2001",
//
//                    ),
//                LatestNewsItems(
//                    "A record 55,000 Bitcoin, or over $1.1 billion, was just withdrawn from Binance",
//                    "Some serious withdrawals are occurring on the world’s biggest crypto exchange by volume",
//                    "01-01-2001",
//
//
//                    ),
//                LatestNewsItems(
//
//                    "A record 55,000 Bitcoin, or over $1.1 billion, was just withdrawn from Binance",
//                    "Some serious withdrawals are occurring on the world’s biggest crypto exchange by volume",
//                    "01-01-2001"
//
//                )
//            ))
//            Spacer(modifier = Modifier.height(15.dp))
            TopTenCoins(navController = navController)





        }
    }
}






@Composable
fun UserGreetings(
    name:String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
          Column(verticalArrangement = Arrangement.Center) {
              Text(
                  text = "Welcome,",
                  color = Color.White,
                  style = MaterialTheme.typography.h6
              )
              Text(
                  text = "$name ",
                  color= Color.LightGray,
                  style = MaterialTheme.typography.h6
              )

          }
        Image(painter = painterResource(id = R.drawable.logo_name), contentDescription = "logo", modifier = Modifier.size(60.dp))
//
    }
}

@Composable
fun SelectedCurrency(

) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
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
                        GlideImage(imageModel = {"https://static.coinpaprika.com/coin/bora-bora/logo.png" },
                            modifier = Modifier.size(60.dp)
                        )
                        Column {
                            Text(
                                text = buildAnnotatedString {
                                    val symbolStyle = SpanStyle(
                                        gold, fontWeight = FontWeight.SemiBold, fontSize = 15.sp
                                    )
                                    append("Ethereum ")
                                    pushStyle(symbolStyle)
                                    append("BTC")
                                },
                                style = MaterialTheme.typography.h5,

                                )
                            Text(
                                text = "$1070",
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
                                text = "1070000000",
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
                                Text(
                                    text = "1.5%",
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1
                                )
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_upward_24) , contentDescription = "indicator", tint = Color.Green)
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
                                Text(
                                    text = "10%",
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1
                                )
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_24) , contentDescription = "indicator", tint = Color.Red)
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
                                Text(
                                    text = "10%",
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1,
                                )
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_24) , contentDescription = "indicator", tint = Color.Red)

                            }
                        }
                    }

                }




            }
        }
    }
}

@Composable
fun SearchBar(
) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(card1)
                .padding(horizontal = 8.dp,)
                .fillMaxWidth()
        ) {
              Text(text = "Search any currency...",
                  style = MaterialTheme.typography.body1,
                  color = search,
                  modifier = Modifier.padding(10.dp)
                  )
            Icon(imageVector = Icons.Default.Search, contentDescription = "search", tint = search, modifier = Modifier.padding(10.dp))

        }

}

@Composable
fun LatestNews(
newsList:List<LatestNewsItems>,
modifier: Modifier = Modifier
) {
     Column(
         modifier = modifier
             .fillMaxWidth()
             .padding(5.dp)
     ) {
         Text(text = "Latest news", style = MaterialTheme.typography.h6, color = Color.White, modifier = modifier.padding(15.dp))
         LazyColumn(contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 50.dp),
         modifier = modifier.fillMaxHeight()
         ){
                items(newsList.size){
                    SingleNewsItem(newsList[it])
                }
         }
     }
}

@Composable
fun SingleNewsItem(
    news:LatestNewsItems
) {
      Column(
          verticalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier
              .padding(5.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(newsBackground)
              .padding(horizontal = 8.dp,)
              .fillMaxWidth()
      ) {
          Column(
              modifier = Modifier.padding(8.dp)
          ) {
              Text(text = news.title, style = MaterialTheme.typography.h6, color = title)
              Text(text = news.lead,fontSize = 15.sp, style = MaterialTheme.typography.body1, color = Color.LightGray)
          }


          Row(
              horizontalArrangement = Arrangement.SpaceBetween,
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(8.dp)
          ) {
              Text(
                  text = news.date,
                  fontSize = 12.sp,
                  style = MaterialTheme.typography.body1,
                  color = Color.LightGray,
                  modifier = Modifier.align(Alignment.Bottom)
              )
              Text(
                  text = "Read",
                  fontSize = 15.sp,
                  style = MaterialTheme.typography.body1,
                  color = Color.Blue,
                  modifier = Modifier
                      .align(Alignment.Bottom)
                      .clickable { }
              )
          }
      }

}



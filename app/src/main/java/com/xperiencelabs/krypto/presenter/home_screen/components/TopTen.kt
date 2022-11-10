package com.xperiencelabs.krypto.presenter.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import com.xperiencelabs.krypto.domain.model.Coin
import com.xperiencelabs.krypto.presenter.LottieAnimation
import com.xperiencelabs.krypto.presenter.Screen
import com.xperiencelabs.krypto.presenter.home_screen.MainViewModel
import com.xperiencelabs.krypto.presenter.theme.cardGradient1
import com.xperiencelabs.krypto.presenter.theme.cardGradient2
import com.xperiencelabs.krypto.presenter.theme.gold
import com.xperiencelabs.krypto.presenter.theme.gradient2


@Composable
fun TopTenCoins(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val  state = viewModel.state.value
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
                            navController.navigate(Screen.CoinDetail.route + "/${coin.id}")
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

@Composable
fun CoinCard(
    onItemClick: (Coin) -> Unit,
    coin: Coin
) {
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
            GlideImage(imageModel = {"https://static.coinpaprika.com/coin/nexo-nexo/logo.png" },
                modifier = Modifier.size(35.dp))
            Text(text = coin.name, style = MaterialTheme.typography.body1, color = gold, modifier = Modifier.padding(start = 10.dp))
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "$20,000", style = MaterialTheme.typography.h5, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column {
                Text(text = "Market Cap", fontSize = 10.sp, color = Color.LightGray)
                Text(text = "392B", style = MaterialTheme.typography.body1, color = Color.White)
            }
            Text(text = "Active", style = MaterialTheme.typography.body1, color = Color.Green)
        }
    }
}

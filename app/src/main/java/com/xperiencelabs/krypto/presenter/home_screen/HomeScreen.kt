package com.xperiencelabs.krypto.presenter.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.presenter.home_screen.components.CurrencyGrid
import com.xperiencelabs.krypto.presenter.home_screen.components.SelectedCurrency
import com.xperiencelabs.krypto.presenter.home_screen.components.TopTenCoins
import com.xperiencelabs.krypto.presenter.news.LatestNews
import com.xperiencelabs.krypto.presenter.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController,
             ) {
    val gradientColor = Brush.verticalGradient(listOf(gradient1, gradient2))
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState=scaffoldState) {

        Column(modifier = Modifier
            .background(gradientColor)
            .fillMaxSize())
        {

//               UserGreetings(name = "Prince Kumar")
//            Spacer(modifier = Modifier.height(15.dp))
//
//            CurrencyGrid()
//            SearchBar()
//               LatestNews()
//               Spacer(modifier = Modifier.height(15.dp))
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





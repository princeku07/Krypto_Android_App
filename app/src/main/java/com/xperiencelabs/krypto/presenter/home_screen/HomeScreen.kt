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
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.presenter.Screen_routes
import com.xperiencelabs.krypto.presenter.home_screen.components.CurrencyGrid
import com.xperiencelabs.krypto.presenter.home_screen.components.TopTenCoins
import com.xperiencelabs.krypto.presenter.news.LatestNews
import com.xperiencelabs.krypto.presenter.theme.*
import com.xperiencelabs.krypto.utils.BottomNavItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController,
             ) {
    val gradientColor = Brush.verticalGradient(listOf(gradient1, gradient2))
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

        LazyColumn(modifier = Modifier
            .background(gradientColor)
            .fillMaxSize())
        {
          item {
              UserGreetings(name = "Prince Kumar")
              Spacer(modifier = Modifier.height(15.dp))

              CurrencyGrid(navController)
              SearchBar()
              LatestNews()
              Spacer(modifier = Modifier.height(15.dp))


          }








        }
    }
}


@Composable
fun BottomBar(
items:List<BottomNavItem>,
navController: NavController,
modifier: Modifier = Modifier,
onItemClick:(BottomNavItem) -> Unit
) {
    val backStack = navController.currentBackStackEntryAsState()
      BottomNavigation(
          modifier = modifier,
          backgroundColor = gradient1,
          elevation = 50.dp
      ) {
          items.forEach{
              val selected = it.route == backStack.value?.destination?.route
              BottomNavigationItem(selected = selected,
                  onClick = { onItemClick(it)},
                  selectedContentColor = Color(0xFFFFFFFF),
                  unselectedContentColor =Color(0x86ABC0FF) ,
                  icon = {
                      Column(
                          horizontalAlignment = CenterHorizontally
                      ) {
                            if (it.badgeCount >0){
                               BadgedBox(badge = {
                                   Text(text = it.badgeCount.toString(), fontSize = 10.sp, color = Color.Red)
                               }) {
                                   Icon(imageVector = it.icon, contentDescription = it.name)
                               }
                            } else {
                                Icon(imageVector = it.icon, contentDescription = it.name )
                            }
                          if (selected){
                              Text(text = it.name,
                              textAlign = TextAlign.Center,
                              color=Color.White,
                              fontSize = 10.sp)
                          }
                      }
                  }
              )

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





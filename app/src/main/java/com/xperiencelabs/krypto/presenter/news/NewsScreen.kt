package com.xperiencelabs.krypto.presenter.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xperiencelabs.krypto.data.remote.dto.NewsEntity
import com.xperiencelabs.krypto.presenter.theme.newsBackground
import com.xperiencelabs.krypto.presenter.theme.title

@Composable
fun LatestNews(
    modifier: Modifier = Modifier,
   viewModel: NewsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(text = "Latest news", style = MaterialTheme.typography.h6, color = Color.White, modifier = modifier.padding(15.dp))
//        LazyColumn(contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 50.dp),
//            modifier = modifier.fillMaxHeight()
//        ){
//            items(state.news){news ->
//                SingleNewsItem(news = news)
//
//            }
//        }
        Column {
            state.news.forEach{news->
                SingleNewsItem(news = news)
            }
        }
    }
}

@Composable
fun SingleNewsItem(
    news: NewsEntity
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
                text = news.newsDate.reversed().substring(10),
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
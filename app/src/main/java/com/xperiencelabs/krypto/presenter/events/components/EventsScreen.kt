package com.xperiencelabs.krypto.presenter.events.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.data.remote.dto.EventsEntity
import com.xperiencelabs.krypto.presenter.events.EventsViewModel
import com.xperiencelabs.krypto.presenter.theme.gradient1
import com.xperiencelabs.krypto.presenter.theme.gradient2

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EventScreen(
    viewModel: EventsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Column {
                Text(text = "Events", style = MaterialTheme.typography.h5, modifier = Modifier.padding(15.dp))

                LazyColumn() {
                    items(state.events){event->
                        Log.d("Event","$event")
                        if (event.name == null){
                            NoEvent()
                        }else{
                            EventItem(event = event)
                        }


                    }

                }
            }

        }
    }

}

@Composable
fun EventItem (
    event: EventsEntity,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 7.5.dp, end = 7.5.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(gradient1)
    ) {
       GlideImage(imageModel = {
           event.proof_image_link
               ?: "https://raw.githubusercontent.com/princeku07/Krypto_Android_App/UI/app/src/main/res/drawable/image_not_found.png"
       },
       modifier = Modifier.size(400.dp,200.dp))
        Text(text = event.name, style = MaterialTheme.typography.h6, modifier = Modifier.padding(10.dp))
        Text(
            text = event.description,
            style = MaterialTheme.typography.body1,
            color = Color.LightGray, modifier = Modifier.padding(10.dp)
        )
        Row(
            verticalAlignment=Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(text = event.date.toString(), fontSize = 10.sp,color=Color.LightGray)
            Button(onClick = { /*TODO*/ }) {
                Text(text = "visit")
            }
        }
    }
}
@Composable
fun NoEvent() {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.no_event), contentDescription = "no event", modifier = Modifier.size(450.dp,400.dp))

    }
}
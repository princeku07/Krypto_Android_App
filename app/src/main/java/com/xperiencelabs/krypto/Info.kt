package com.xperiencelabs.krypto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Info() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(25.dp)
        ){
        Image(painter = painterResource(id = R.drawable.logo_name), contentDescription = ""
        )
        Text(text = "Krypto is app which updates with current crypto market variations ", style = MaterialTheme.typography.h6,
       )
            }
}
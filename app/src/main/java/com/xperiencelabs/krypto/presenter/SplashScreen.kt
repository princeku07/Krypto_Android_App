package com.xperiencelabs.krypto.presenter

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xperiencelabs.krypto.R
import com.xperiencelabs.krypto.presenter.theme.SplashText
import com.xperiencelabs.krypto.presenter.theme.gradient1
import com.xperiencelabs.krypto.presenter.theme.gradient2
import kotlinx.coroutines.delay




@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    var isVisible by remember {
        mutableStateOf(false)
    }

    //Image Animation
    AnimatedVisibility(visible = isVisible, enter = slideIn(
        initialOffset = {
            IntOffset(it.width,it.height/2)
    },
        animationSpec = tween(
            2000, easing = LinearEasing
        )), exit = slideOut(targetOffset = {
            IntOffset(-it.width,it.height/2)
    }, animationSpec = tween(
        2000, easing = LinearEasing
    ))
    ) {
        Image(painter = painterResource(id = R.drawable.golden_pattern),
            contentDescription = "Logo",
            modifier = Modifier

                .size(300.dp, 800.dp)
        )
    }
    
    // AnimationEffect logo
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(route = Screen_routes.HomeScreen.route)
    }

    val gradient = Brush.verticalGradient(listOf(gradient1, gradient2))
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(gradient))
    {
        Image(painter = painterResource(id = R.drawable.logo_name), contentDescription = "logo",
        modifier = Modifier
            .scale(scale.value)
            .size(190.dp)
            .align(Alignment.BottomCenter)
            .padding(bottom = 20.dp))
        
        Image(painter = painterResource(id = R.drawable.golden_pattern),
            contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(300.dp, 800.dp)
           )
        Text(text = "Update yourself with latest Crypto news.", style = MaterialTheme.typography.body1,
            color = SplashText,
            modifier = Modifier
                .padding(bottom = 50.dp)
                .align(Alignment.BottomCenter)
        )
    }
}
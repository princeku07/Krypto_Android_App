package com.xperiencelabs.krypto.presenter.currency_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.xperiencelabs.krypto.presenter.theme.card
import com.xperiencelabs.krypto.presenter.theme.search

@Composable
fun TabItems(
    modifier: Modifier = Modifier,
    tabs:List<Tabs>,
    onTabSelected:(selectedIndex:Int) -> Unit
) {
         val selectedTabIndex by remember {
             mutableStateOf(0)
         }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = card,
        contentColor = search,
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        tabs.forEachIndexed { index, tabs -> 
            Tab(selected = selectedTabIndex ==index ,
                onClick = { 
                    selectedTabIndex == index
                    onTabSelected(index)
                     }
            ) {
               Row {
                   Icon(painter = tabs.icon, contentDescription = null)
               }
            }
        }
    }
}
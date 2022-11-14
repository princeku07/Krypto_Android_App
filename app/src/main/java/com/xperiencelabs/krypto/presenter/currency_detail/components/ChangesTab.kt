package com.xperiencelabs.krypto.presenter.currency_detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xperiencelabs.krypto.presenter.theme.cardGradient2
import com.xperiencelabs.krypto.presenter.theme.gradient1
import com.xperiencelabs.krypto.presenter.theme.gradient2
import java.util.Locale.Category

@Composable
fun ChangeTab(
    modifier: Modifier = Modifier,
    tabs:List<Tabs>,
    onTabSelected:(SelectedIndex:Int) -> Unit
) {
      var selectedTabIndex by remember {
          mutableStateOf(0)
      }
    val activeColor = Color(0xFFD4ECFF)
    val inactiveColor = Color(0xFF4E4E4E)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color(0xFF01031B),
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
        ) {
        tabs.forEachIndexed{index,item ->
            Tab(selected = selectedTabIndex==index,
                selectedContentColor = Color.White,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }) {
                Text(text = item.Factor,
                    fontSize = if (selectedTabIndex == index) 20.sp else 15.sp, modifier = Modifier.padding(5.dp),
                    color = if(selectedTabIndex == index) activeColor else inactiveColor

                )
            }

        }
    }
}
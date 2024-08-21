package com.igordudka.ui.widgets.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.igordudka.ui.R
import com.igordudka.ui.components.SemiboldText
import com.igordudka.ui.components.barColor
import com.igordudka.ui.components.darkGray

@Composable
fun BottomBarWidget(
    screen: String,
    onClick: (String) -> Unit,
    screens: List<String>
) {


    Row(
        Modifier
            .background(Color.White)
            .navigationBarsPadding()
            .fillMaxWidth()
            .border(0.4.dp, barColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BarBottom(
            text = "Поиск",
            alpha = if (screen == screens[0]) 1f else 0.3f,
            icon = R.drawable.search_tapped
        ) {
            onClick(screens[0])
        }
        BarBottom(
            text = "Команда",
            alpha = if (screen == screens[1]) 1f else 0.3f,
            icon = R.drawable.team_tapped
        ) {
            onClick(screens[1])
        }
        BarBottom(
            text = "Профиль",
            alpha = if (screen == screens[2]) 1f else 0.3f,
            icon = R.drawable.profile_tapped
        ) {
            onClick(screens[2])
        }
    }
}

@Composable
fun BarBottom(
    text: String,
    alpha: Float,
    icon: Int,
    onClick: () -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)) {
        IconButton(onClick = onClick) {
            Image(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                Modifier
                    .size(24.dp)
                    .alpha(alpha)
            )
        }
        SemiboldText(text = text, size = 13, color = Color.Black.copy(alpha = alpha),
            padding = 0)
    }
}
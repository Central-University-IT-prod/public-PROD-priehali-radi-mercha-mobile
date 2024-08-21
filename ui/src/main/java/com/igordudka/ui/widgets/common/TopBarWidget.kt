package com.igordudka.ui.widgets.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.igordudka.ui.R
import com.igordudka.ui.components.SemiboldText
import com.igordudka.ui.components.borderGray

@Composable
fun TopBarWidget(
    name: String
) {

    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically) {
            SemiboldText(text = name, size = 20)
            Spacer(modifier = Modifier.weight(1f))
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.logo), contentDescription = null)
        }
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .height(0.2.dp), color = borderGray)
    }
}
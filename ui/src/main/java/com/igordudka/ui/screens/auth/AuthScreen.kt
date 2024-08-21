package com.igordudka.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.igordudka.ui.R
import com.igordudka.ui.components.DefaultButton
import com.igordudka.ui.components.blue
import com.igordudka.ui.widgets.auth.Sheet
import com.igordudka.ui.widgets.common.AppButton

@Composable
fun AuthScreen(
    login: () -> Unit,
    register: () -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(imageVector = ImageVector.vectorResource(R.drawable.logo), contentDescription = null,
            Modifier.size(112.dp, 38.dp))
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(text = "Вход", modifier = Modifier.weight(1f)) {
                    login()
                }
            }
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Зарегестрироваться",
                    modifier = Modifier.weight(1f),
                    bgColor = blue
                ) {
                    register()
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}
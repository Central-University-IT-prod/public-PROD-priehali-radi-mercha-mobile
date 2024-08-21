package com.igordudka.ui.widgets.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.TeamDto
import com.igordudka.ui.components.BoldText
import com.igordudka.ui.components.MediumText
import com.igordudka.ui.components.RegularText
import com.igordudka.ui.components.borderGray
import com.igordudka.ui.components.cardColors
import com.igordudka.ui.components.darkGray
import com.igordudka.ui.components.gray
import com.igordudka.ui.widgets.common.AppButton

@Composable
fun FeedTeamCard(
    teamUIDto: TeamDto,
    showMore: () -> Unit,
    match: String
) {

    Column(
        Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(teamUIDto.color.copy(alpha = 0.5f))
            .border(0.3.dp, borderGray, RoundedCornerShape(12.dp))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            BoldText(text = teamUIDto.name, size = 24, padding = 12)
            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .height(0.3.dp), color = borderGray
            )
        }
        Column(Modifier.background(Color.White).padding(12.dp)) {
            RegularText(text = "description", size = 16)
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                MediumText(text = "Мэтч: $match%", size = 14, color = darkGray)
                Spacer(modifier = Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
                AppButton(text = "Подробнее", modifier = Modifier.weight(1f)) {
                    showMore()
                }
            }
        }
    }
}
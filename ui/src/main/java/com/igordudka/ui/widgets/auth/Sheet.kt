package com.igordudka.ui.widgets.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.igordudka.ui.components.gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sheet(
    content: @Composable () -> Unit
) {
    val state = rememberBottomSheetScaffoldState()

    LaunchedEffect(true) {
        state.bottomSheetState.expand()
    }

    BottomSheetScaffold(sheetContent = {
        content()
    }, scaffoldState = state,
        sheetContainerColor = gray) {}
}
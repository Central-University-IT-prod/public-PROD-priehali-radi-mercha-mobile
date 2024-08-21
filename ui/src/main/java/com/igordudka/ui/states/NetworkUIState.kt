package com.igordudka.ui.states

sealed interface NetworkUIState {
    data object Success: NetworkUIState
    data object Loading: NetworkUIState
    data object Failed: NetworkUIState
}
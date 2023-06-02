package com.example.unitconverter.compose.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.unitconverter.data.ConversionResult

@Composable
fun HistoryScreen(
    list: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onCloseTask: (ConversionResult) -> Unit
) {
    HistoryList(list = list, onCloseTask = { onCloseTask(it) })
}
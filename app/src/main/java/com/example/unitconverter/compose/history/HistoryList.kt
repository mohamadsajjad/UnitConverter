package com.example.unitconverter.compose.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.unitconverter.data.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onCloseTask: (ConversionResult) -> Unit
) {
    CustomBorderedBoxWithLabel(label = "History") {
        LazyColumn() {
            items(
                count = list.value.size,
                key = { list.value[it].id },
                itemContent = {
                    HistoryItem(
                        conversionResult = list.value[it],
                        onClose = { onCloseTask(list.value[it]) })
                }
            )
        }

    }
}


@Composable
private fun CustomBorderedBoxWithLabel(label: String, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(top = 10.dp)) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .fillMaxSize().padding(top = 10.dp)
            ) {
                content()
            }
        }
        Box(modifier = Modifier.padding(start = 14.dp)) {
            Text(
                text = label,
                color = Color.Gray,
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = 3.dp, end = 3.dp)
            )
        }
    }
}
package com.example.unitconverter.compose.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.unitconverter.data.ConversionResult

@Composable
fun HistoryItem(
    conversionResult: ConversionResult,
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = modifier.padding(8.dp, 0.dp, 8.dp, 0.dp)) {
                Text(
                    text = conversionResult.convertedFrom +
                            " " +
                            conversionResult.convertedFromType +
                            " is equal to "
                )
                Text(
                    text = conversionResult.convertedTo + " " + conversionResult.convertedToType,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(onClick = { onClose() }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "close")
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp)
                .alpha(0.3F)
        ) {
            Divider(color = Color.LightGray, thickness = 0.8.dp)
        }
    }
}
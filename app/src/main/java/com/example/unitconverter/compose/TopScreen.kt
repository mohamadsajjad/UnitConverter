package com.example.unitconverter.compose

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.unitconverter.Conversion
import kotlinx.coroutines.CoroutineScope
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.log

@Composable
fun TopScreen(
    list: List<Conversion>,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    Log.i("***mss unitconverter.compose.TopScreen", "TopScreen: start")

    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }
    val typedValue = remember { mutableStateOf("0.0") }


    ConversionMenu(list = list) {
        selectedConversion.value = it
    }

    selectedConversion.value?.let {
        InputBlock(
            conversion = it,
            inputText = inputText,
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope
        ) { input ->
            typedValue.value = input
        }
    }


    if (typedValue.value != "0.0") {
        val input = typedValue.value.toDouble()
        val multiply = selectedConversion.value!!.multiplyBy
        val result = input * multiply

        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.DOWN
        val roundedResult = df.format(result)

        ResultBlock(
            convertedTo = selectedConversion.value!!.convertTo,
            result = roundedResult,
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope
        )
    }
    Log.i("***mss unitconverter.compose.TopScreen", "TopScreen: end")
}
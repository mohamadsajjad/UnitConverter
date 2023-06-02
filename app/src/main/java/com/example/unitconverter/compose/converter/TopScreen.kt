package com.example.unitconverter.compose.converter

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.unitconverter.data.Conversion
import com.example.unitconverter.data.ConversionResult
import kotlinx.coroutines.CoroutineScope
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    save: (ConversionResult) -> Unit
) {
    Log.i("***mss unitconverter.compose.TopScreen", "TopScreen: start")

    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }
    val typedValue = remember { mutableStateOf("0.0") }
    var roundedResult = ""


    if (typedValue.value != "0.0") {
        val input = typedValue.value.toDouble()
        val multiply = selectedConversion.value!!.multiplyBy
        val result = input * multiply

        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.DOWN
        roundedResult = df.format(result)

        save(
            ConversionResult(
                0,
                input.toString(),
                selectedConversion.value!!.convertFrom,
                roundedResult,
                selectedConversion.value!!.convertTo
            )
        )

    }

    ConversionMenu(list = list) {
        selectedConversion.value = it
        typedValue.value = "0.0"
    }


    InputBlock(
        conversionFrom = selectedConversion.value?.convertFrom,
        inputText = inputText,
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope
    ) { input ->
        typedValue.value = input
    }

    ResultBlock(
        convertedTo = selectedConversion.value?.convertTo,
        result = roundedResult,
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope
    )


    Log.i("***mss unitconverter.compose.TopScreen", "TopScreen: end")
}
package com.example.unitconverter.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ConverterViewModel
import com.example.unitconverter.compose.converter.TopScreen
import com.example.unitconverter.compose.history.HistoryScreen

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel
) {
    val list = converterViewModel.getConversions()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val historyList = converterViewModel.historyList.collectAsState(initial = emptyList())

//    val insertedRowIdDB = converterViewModel.insertedRowIdDB.collectAsState()
//
//    Log.i(
//        "***mss unitconverter.compose.BaseScreen",
//        "BaseScreen: ${insertedRowIdDB.value}"
//    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { paddingValue ->
        val a = paddingValue

        /*when (screenState.value) {

            is ScreenStateHandler.Init -> {

            }

            is ScreenStateHandler.Loading -> {
                Box(modifier = modifier.fillMaxSize()){
                    CircularProgressIndicator(
                        modifier = modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            is ScreenStateHandler.Success -> {
                screenState.value.data?.let {
                    it.getContentIfNotHandled()?.let { insertedRowId ->
                        coroutineScope.launch {
                            snackBarHostState.showSnackbar(
                                message = "conversion added to history with id:$insertedRowId"
                            )
                        }
                    }
                }
            }

            is ScreenStateHandler.Error -> {

            }

        }*/

        Column(
            modifier = modifier
                .padding(30.dp)
                .fillMaxSize()
        ) {
            TopScreen(
                list,
                snackBarHostState,
                coroutineScope
            ) {
                converterViewModel.saveResult(it)
            }
            Spacer(modifier = modifier.height(20.dp))
            HistoryScreen(historyList){
                converterViewModel.deleteResult(it)
            }
        }
    }
}
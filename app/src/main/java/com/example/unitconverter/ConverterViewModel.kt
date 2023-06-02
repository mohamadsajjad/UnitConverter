package com.example.unitconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverter.data.Conversion
import com.example.unitconverter.data.ConversionResult
import com.example.unitconverter.data.ConverterRepository
import com.example.unitconverter.data.util.ContentHandler
import com.example.unitconverter.data.util.ScreenStateHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConverterViewModel(private val repository: ConverterRepository) : ViewModel() {

    fun getConversions() = listOf(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles to Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers to Miles", "km", "mi", 0.621371)
    )

    val historyList = repository.getSavedResults()

    private val _insertedRowIdDB =
        MutableStateFlow<Long>(0L)
    val insertedRowIdDB: StateFlow<Long>
        get() = _insertedRowIdDB

    fun saveResult(conversionResult: ConversionResult) = viewModelScope.launch(Dispatchers.IO) {
        val rowId = insertAsync(conversionResult)
        _insertedRowIdDB.value = rowId.await()
    }

    private suspend fun insertAsync(conversionResult: ConversionResult): Deferred<Long> {
        val rowId: Deferred<Long>
        coroutineScope {
            rowId = async(Dispatchers.IO) {
                return@async repository.insert(conversionResult)
            }
        }
        return rowId
    }

    fun deleteResult(conversionResult: ConversionResult) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(conversionResult)
    }
}
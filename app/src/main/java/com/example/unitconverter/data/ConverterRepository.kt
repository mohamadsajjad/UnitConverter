package com.example.unitconverter.data

import kotlinx.coroutines.flow.Flow

interface ConverterRepository{
    suspend fun insert(conversionResult: ConversionResult):Long
    suspend fun delete(conversionResult: ConversionResult)
    suspend fun deleteAll()
    fun getSavedResults(): Flow<List<ConversionResult>>
}
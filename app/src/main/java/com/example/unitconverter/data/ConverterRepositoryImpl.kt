package com.example.unitconverter.data

import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(private val converterDao: ConverterDao):ConverterRepository {
    override suspend fun insert(conversionResult: ConversionResult):Long {
        return converterDao.insert(conversionResult)
    }

    override suspend fun delete(conversionResult: ConversionResult) {
        converterDao.delete(conversionResult)
    }

    override suspend fun deleteAll() {
        converterDao.deleteAll()
    }

    override fun getSavedResults(): Flow<List<ConversionResult>> {
        return converterDao.getSavedResults()
    }
}
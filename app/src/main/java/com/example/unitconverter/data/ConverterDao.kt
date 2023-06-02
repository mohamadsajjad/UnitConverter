package com.example.unitconverter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConverterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversionResult: ConversionResult):Long

    @Delete
    suspend fun delete(conversionResult: ConversionResult)

    @Query("DELETE FROM result_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM result_table")
    fun getSavedResults(): Flow<List<ConversionResult>>
}
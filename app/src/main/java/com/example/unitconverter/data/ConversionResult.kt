package com.example.unitconverter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_table")
data class ConversionResult(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val convertedFrom:String,
    val convertedFromType:String,
    val convertedTo:String,
    val convertedToType:String,
)

package com.example.unitconverter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ConversionResult::class],
    version = 1
)
abstract class ConverterDB : RoomDatabase() {
    abstract val converterDao:ConverterDao

    companion object{
        @Volatile
        private var INSTANCE:ConverterDB?=null
        fun getInstance(context: Context):ConverterDB{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConverterDB::class.java,
                        "converter_history"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
package com.example.unitconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unitconverter.data.ConverterRepository

class ConverterViewModelFactory(private val repository: ConverterRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConverterViewModel::class.java)){
            return ConverterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}
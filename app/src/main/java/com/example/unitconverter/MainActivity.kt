package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.unitconverter.compose.BaseScreen
import com.example.unitconverter.data.ConverterDB
import com.example.unitconverter.data.ConverterRepositoryImpl

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ConverterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = ConverterDB.getInstance(application).converterDao
        val repository = ConverterRepositoryImpl(dao)
        val factory = ConverterViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[ConverterViewModel::class.java]

        setContent {
            BaseScreen(converterViewModel=viewModel)
        }
    }
}

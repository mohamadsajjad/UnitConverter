package com.example.unitconverter.data.util


sealed class ScreenStateHandler<out T>(val data: ContentHandler<T>? = null, val message: String? = null) {

    class Init<T>():ScreenStateHandler<T>()

    class Loading<T>(data: ContentHandler<T>? = null) : ScreenStateHandler<T>(data)

    class Success<T>(data: ContentHandler<T>) : ScreenStateHandler<T>(data)

    class Error<T>(message: String, data: ContentHandler<T>? = null) : ScreenStateHandler<T>(data, message)

}



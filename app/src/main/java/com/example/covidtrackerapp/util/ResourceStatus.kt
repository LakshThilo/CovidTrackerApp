package com.example.covidtrackerapp.util

sealed class ResourceStatus<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : ResourceStatus<T>(data)
    class Error<T>(message: String, data: T? =null) : ResourceStatus<T>(data, message)
    class Loading<T> :ResourceStatus<T>()
}
package com.app.core.domain

sealed class ResultWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    data class Error(val exception : Exception) : ResultWrapper<Nothing>()
    data class ErrorString(val exception : String) : ResultWrapper<Nothing>()
    object InProgress : ResultWrapper<Nothing>()
}
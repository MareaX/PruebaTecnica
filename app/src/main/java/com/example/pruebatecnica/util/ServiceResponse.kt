package com.example.pruebatecnica.util

sealed class ServiceResponse<T> {
    data class Successful<T>(val content: T, val code: Int? = null, val message: String? = null) : ServiceResponse<T>()
    data class Failed<T>(val errorMessage: String, val content: T? = null, val codeStr: String? = null) :
        ServiceResponse<T>()
}
package com.nisa.newsappid.utils

sealed class Resource<out R> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Error<T>(val message: String, val data: T? = null): Resource<T>()
    object Empty : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
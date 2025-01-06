package com.gbl.lenenergopoweroutage.domain.model

sealed class DataState<T> {
    class Cached<T>(val data: T) : DataState<T>()
    class Online<T>(val data: T) : DataState<T>()
    class Error<T>(val exception: Exception) : DataState<T>()
}
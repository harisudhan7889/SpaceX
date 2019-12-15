package com.spacex.launch.common.model

/**
 * @author Hari Hara Sudhan.N
 */
sealed class ViewState<T> {
    abstract val data: List<T>
}

data class LoadingState<T>(override val data: List<T>): ViewState<T>()
data class DataState<T>(override val data: List<T>): ViewState<T>()
data class ErrorState<T>(val error: Throwable, override val data: List<T>): ViewState<T>()

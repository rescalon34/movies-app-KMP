package com.escalondev.movies_app_kmp.domain.util

import com.escalondev.movies_app_kmp.data.model.base.ErrorMessageResponse
import com.escalondev.movies_app_kmp.domain.model.ErrorMessage

/**
 * Wrapper class to return a Success or Failure result.
 * If successful, the NetworkResult<T> class will exposed the Domain data to the consumers.
 */
sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Failure(val errorMessage: ErrorMessageResponse? = null) : NetworkResult<Nothing>()
}

/**
 * Helper function to get success result data after executing the api request on the consumer side.
 *
 * Example:
 * ```
 * useCase()
 *   .onSuccess { data ->
 *       // Rest of the code.
 *   }
 *   .onFailure { _ -> }
 * ```
 */
inline fun <T : Any> NetworkResult<T>.onSuccess(action: (T) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) action(data)
    return this
}

/**
 * Maps the current `NetworkResult` of type `T` to a new `NetworkResult` of type `R` by applying
 * a transformation function to the success data.
 *
 * @param T The input data type of the current result.
 * @param R The output data type after applying the transformation.
 * @param transform The transformation function to apply to the successful result data.
 * @return A new `NetworkResult` of type `R`, either a success with transformed data or a failure.
 *
 * Example:
 * ```
 * useCase().mapToDomainResult { data -> data.map { it.toDomainObject() } } }
 * ```
 */
inline fun <T : Any, R : Any> NetworkResult<T>.mapToDomainResult(
    transform: (T) -> R
): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(this.data))
        is NetworkResult.Failure -> NetworkResult.Failure(this.errorMessage)
    }
}

/**
 * Helper function to get a failure result message after executing the api request on the consumer side.
 *
 * Example:
 * ```
 * useCase()
 *   .onSuccess { _ -> }
 *   .onFailure { error ->
 *       // Rest of the code
 *   }
 * ```
 */
inline fun <T : Any> NetworkResult<T>.onFailure(action: (ErrorMessage?) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Failure) {
        action(errorMessage?.toDomain())
    }
    return this
}

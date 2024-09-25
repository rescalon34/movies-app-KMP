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
 * sharedCoreManager.useCaseProvider.useCase()
 *       .onSuccess { data ->
 *            // Rest of the code.
 *       }
 *       .onFailure { _ -> }
 * ```
 */
inline fun <T : Any> NetworkResult<T>.onSuccess(action: (T) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) action(data)
    return this
}

/**
 * Helper function to get a failure result message after executing the api request on the consumer side.
 *
 * Example:
 * ```
 * sharedCoreManager.useCaseProvider.useCase()
 *       .onSuccess { _ -> }
 *       .onFailure { error ->
 *           // Rest of the code
 *      }
 * ```
 */
inline fun <T : Any> NetworkResult<T>.onFailure(action: (ErrorMessage?) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Failure) {
        action(errorMessage?.toDomain())
    }
    return this
}

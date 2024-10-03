package com.escalondev.movies_app_kmp.data.model.base

import com.escalondev.movies_app_kmp.data.networking.safeApiRequest
import io.ktor.client.statement.HttpResponse

/**
 * Wrapper class to handle API requests with generic types: `BaseHttpResponse<T>`.
 *
 * This is required because Ktor's `HttpResponse` doesn't support generic types directly.
 * By using `BaseHttpResponse<T>`, you can deserialize the response into the specified type `T`.
 *
 * @see safeApiRequest for usage reference.
 */
internal data class BaseHttpResponse<T>(
    val httpResponse: HttpResponse
)

package com.escalondev.movies_app_kmp.data.networking

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.data.model.base.BaseHttpResponse
import com.escalondev.movies_app_kmp.data.model.base.ErrorMessageResponse
import com.escalondev.movies_app_kmp.data.networking.expectactual.toPlatformSpecificException
import com.escalondev.movies_app_kmp.data.util.HttpStatusCodeRange
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

/**
 * A helper function to safely execute an API request and map the response to a domain object.
 *
 * This function wraps an API request inside a `try-catch` block to handle errors such as serialization issues.
 * It checks if the response status is within the success range (2xx). If successful, it maps the response body
 * to a domain object using the provided `DomainMapper<U>`. If the response is an error, it decodes the error message
 * and returns a failure result.
 *
 * @param T The type of the response that implements the [DomainMapper] interface to be mapped to a domain object.
 * @param U The type of the domain object to which the API response will be mapped.
 * @param apiRequest A lambda that returns the [BaseHttpResponse] containing the API response.
 *
 * @return [NetworkResult] containing either a success with the mapped domain object or a failure with the error message.
 *
 * @throws SerializationException If the response cannot be serialized or deserialized.
 */
internal suspend inline fun <reified T : DomainMapper<U>, U> safeApiRequest(
    apiRequest: () -> BaseHttpResponse<T>,
): NetworkResult<U> {
    return try {
        val response = apiRequest().httpResponse
        when (response.status.value) {
            in HttpStatusCodeRange.SUCCESS.range -> NetworkResult.Success(
                response.body<T>().toDomain()
            )

            else -> {
                val error = getJsonDecoder()
                    .decodeFromString<ErrorMessageResponse>(response.bodyAsText())
                NetworkResult.Failure(error)
            }
        }
    } catch (e: SerializationException) {
        NetworkResult.Failure(
            errorMessage = ErrorMessageResponse(
                statusCode = e.hashCode(),
                statusMessage = e.message.orEmpty()
            )
        )
    } catch (throwable: Throwable) {
        handlePlatformExceptionError(throwable)
    }
}

/**
 * This function will return the specific NetworkResult.Failure message provided by each platform.
 *
 * @param throwable represents the main exception that will be used by each platform to catch
 * the specific exception.
 */
internal fun handlePlatformExceptionError(throwable: Throwable): NetworkResult.Failure {
    return throwable.toPlatformSpecificException()?.let { platformException ->
        NetworkResult.Failure(
            errorMessage = ErrorMessageResponse(
                statusCode = platformException.code ?: throwable.hashCode(),
                statusMessage = platformException.message ?: throwable.message.orEmpty()
            )
        )
    } ?: run {
        NetworkResult.Failure(
            errorMessage = ErrorMessageResponse(
                statusCode = throwable.hashCode(),
                statusMessage = throwable.message.orEmpty()
            )
        )
    }
}

/**
 * JsonDecoder function with the `ignoreUnknownKeys` to ignore unknown properties in the input JSON.
 */
internal fun getJsonDecoder(): Json {
    return Json {
        ignoreUnknownKeys = true
    }
}

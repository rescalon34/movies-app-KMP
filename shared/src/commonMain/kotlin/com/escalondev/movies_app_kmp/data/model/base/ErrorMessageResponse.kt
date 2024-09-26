package com.escalondev.movies_app_kmp.data.model.base

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.ErrorMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing the error format returned by the API in case of a failure.
 *
 * Maps the API error response to a domain model using `DomainMapper`.
 */
@Serializable
data class ErrorMessageResponse(
    @SerialName("status_code")
    val statusCode: Int,
    @SerialName("status_message")
    val statusMessage: String
) : DomainMapper<ErrorMessage> {
    override fun toDomain() = ErrorMessage(
        statusCode = statusCode,
        statusMessage = statusMessage
    )
}

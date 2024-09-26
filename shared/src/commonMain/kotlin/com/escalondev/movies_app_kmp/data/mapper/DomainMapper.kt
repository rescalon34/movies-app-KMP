package com.escalondev.movies_app_kmp.data.mapper

/**
 * A generic interface for mapping response objects to domain models within the SDK.
 *
 * Domain models are public and exposed externally, while this mapper is meant to be
 * implemented by internal response objects.
 */
internal interface DomainMapper<T : Any> {
    fun toDomain(): T
}

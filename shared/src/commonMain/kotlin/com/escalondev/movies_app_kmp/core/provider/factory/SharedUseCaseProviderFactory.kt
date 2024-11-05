package com.escalondev.movies_app_kmp.core.provider.factory

internal interface SharedUseCaseProviderFactory {
    fun <T : Any> createProvider(providerType: Any): T
}

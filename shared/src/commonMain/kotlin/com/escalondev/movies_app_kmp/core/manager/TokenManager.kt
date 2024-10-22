package com.escalondev.movies_app_kmp.core.manager

/**
 * This object will allow setting the API key and accessToken as well as providing access to read those vaules
 * internally from the SDK.
 */
internal object TokenManager {

    var apiKeyValue: String? = ""
    var accessTokenValue: String? = ""

    fun setApiKey(apiKey: String): TokenManager {
        this.apiKeyValue = apiKey
        return this
    }

    fun setAccessToken(accessToken: String): TokenManager {
        this.accessTokenValue = accessToken
        return this
    }
}

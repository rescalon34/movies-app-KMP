package com.escalondev.movies_app_kmp.core.manager

/**
 * This object will allow setting the API key and accessToken as well as providing access to read those vaules
 * internally from the SDK.
 */
internal object TokenManager {

    var apiKey: String? = null
    var accessToken: String? = null
}

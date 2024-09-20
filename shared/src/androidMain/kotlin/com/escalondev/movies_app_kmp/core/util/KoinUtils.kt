package com.escalondev.movies_app_kmp.core.util

import org.koin.core.context.GlobalContext

/**
 * Extension function to have a handy access to the Koin `GlobalContext`
 * from the consumer side when setting up the SharedCoreManager instance.
 */
fun getKoinGlobalContext() = GlobalContext.get()
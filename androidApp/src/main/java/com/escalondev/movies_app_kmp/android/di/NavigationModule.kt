package com.escalondev.movies_app_kmp.android.di

import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigator
import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Singleton
    @Binds
    fun bindAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator
}

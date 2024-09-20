package com.escalondev.movies_app_kmp.core.manager

import com.escalondev.movies_app_kmp.domain.usecase.GetWatchlistUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * This class is intended to store all useCases instances for better organization.
 */
internal object UseCaseManager : KoinComponent {
    val getWatchlistUseCase: GetWatchlistUseCase by inject<GetWatchlistUseCase>()
}

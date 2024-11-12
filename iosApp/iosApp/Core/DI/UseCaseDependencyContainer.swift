//
//  InteractorDependencyContainer.swift
//  iosApp
//
//  Created by rescalon on 7/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

/// DI container responsible for creating useCases instances.
class UseCaseDependencyContainer {
    
    // MARK: - Variables
    let sharedContainer: SharedDependencyContainer
    
    // MARK: - Init
    /// - Parameter sharedContainer: The DI container providing shared resources and UseCaseProviders
    ///   required by the use cases.
    init(sharedContainer: SharedDependencyContainer) {
        self.sharedContainer = sharedContainer
    }
    
    // MARK: - UseCases instances
    func makeGetWatchlistUseCase() -> GetWatchlistUseCase {
        return GetWatchlistUseCaseImpl(
            moviesUseCaseProvider: sharedContainer.makeMoviesUseCaseProvider()
        )
    }
}

//
//  SharedDependencyContainer.swift
//  iosApp
//
//  Created by rescalon on 7/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Shared

/// Manager that contains all instances from the Shared KMP SDK.
///
// MARK: - for DI
protocol HasSharedKMPManager {
    var sharedKMPManager: SharedKMPManagerType { get }
}

// MARK: - Protocol
protocol SharedKMPManagerType {
    var sharedCoreManager: SharedCoreManager { get }
    func makeMoviesUseCaseProvider() -> MoviesUseCaseProvider
    func makeProfileUseCaseProvider() -> ProfileUseCaseProvider
}

// MARK: - Implementation
class SharedKMPManager: SharedKMPManagerType {
    
    static let shared = SharedKMPManager()
    
    private init() {}
    
    // MARK: - main shared core manager instance
    var sharedCoreManager = SharedCoreManager.companion.getInstance()
    
    // MARK: - UseCaseProvider instances.
    func makeMoviesUseCaseProvider() -> MoviesUseCaseProvider {
        return sharedCoreManager.createMoviesUseCaseProvider()
    }
    
    func makeProfileUseCaseProvider() -> ProfileUseCaseProvider {
        sharedCoreManager.createProfileUseCaseProvider()
    }
    
    func makeMockedMovieRepository() -> MockedMoviesRepository {
        MockedMoviesRepository.shared
    }
}

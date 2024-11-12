//
//  SharedDependencyContainer.swift
//  iosApp
//
//  Created by rescalon on 7/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Shared

/// DI Container to provide instances for all the `UseCaseProvides` from the Shared KMP SDK.
///
/// Create any other exposed Provider within this container.
class SharedDependencyContainer {
    
    // MARK: - Variables
    static let shared = SharedDependencyContainer()
    
    // MARK: - Init
    
    /// Private initializer to enforce singleton pattern.
    private init() {}
    
    lazy var sharedCoreManager: SharedCoreManager = {
        SharedCoreManager.companion.getInstance()
    }()
    
    // MARK: - Shared UseCaseProvider instances.
    
    func makeMoviesUseCaseProvider() -> MoviesUseCaseProvider {
        return sharedCoreManager.createMoviesUseCaseProvider()
    }
}

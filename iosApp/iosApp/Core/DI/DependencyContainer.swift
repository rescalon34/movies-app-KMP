//
//  DependencyContainer.swift
//  iosApp
//
//  Created by rescalon on 11/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

/// Main Dependency Injection (DI) container for the app.
///
/// This `DependencyContainer` serves as the single entry point for accessing dependencies
/// throughout the app, add any other sub-containers within it to group and organize dependencies.
class DependencyContainer {
    
    // MARK: - Variables
    
    static let shared = DependencyContainer()
    let useCaseContainer: UseCaseDependencyContainer
    
    // MARK: - Init
    
    /// Private initializer to enforce singleton pattern.
    private init() {
        self.useCaseContainer = UseCaseDependencyContainer(sharedContainer: .shared)
    }
}

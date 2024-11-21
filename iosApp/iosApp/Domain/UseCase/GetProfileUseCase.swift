//
//  ProfileUseCase.swift
//  iosApp
//
//  Created by rescalon on 19/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

// MARK: - DI
protocol HasGetProfileUseCase {
    var getProfileUseCase: GetProfileUseCaseType { get }
}

// MARK: UseCase protocol
protocol GetProfileUseCaseType {
    func getProfile() -> String
}

// MARK: UseCase implementation
struct GetProfileUseCase: GetProfileUseCaseType {
    
    // MARK: - Dependencies
    typealias Dependencies = HasSharedKMPManager
    private var dependencies: Dependencies
    
    // MARK: Shared instance
    static var shared = GetProfileUseCase(dependencies: GetProfileUseCaseDependencies())
    
    // MARK: - Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
    }
    
    // MARK: - Fetch profile information.
    func getProfile() -> String {
        "rescalon34"
    }
}

// MARK: - Dependencies
struct GetProfileUseCaseDependencies: HasSharedKMPManager {
    var sharedKMPManager: SharedKMPManagerType = SharedKMPManager.shared
}

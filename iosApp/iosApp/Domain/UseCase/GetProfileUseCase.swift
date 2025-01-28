//
//  ProfileUseCase.swift
//  iosApp
//
//  Created by rescalon on 19/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import Shared
import KMPNativeCoroutinesCombine

// MARK: - DI
protocol HasGetProfileUseCase {
    var getProfileUseCase: GetProfileUseCaseType { get }
}

// MARK: UseCase protocol
protocol GetProfileUseCaseType {
    func getProfile() -> AnyPublisher<CustomNetworkResult<Profile>, Error>
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
    func getProfile() -> AnyPublisher<CustomNetworkResult<Profile>, Error> {
        
        let future = createFuture(
            for: ProfileUseCaseProviderNativeKt.getProfile(
                dependencies.sharedKMPManager.makeProfileUseCaseProvider()
            )
        )
        
        return future
            .compactMap { networkResult in
                var result: CustomNetworkResult<Profile>? = nil
                networkResult
                    .onSuccess { data in
                        result = CustomNetworkResult.success(
                            (data as? SharedProfile)?.toProfile()
                        )
                    }
                    .onFailure { errorMessage in
                        result = CustomNetworkResult.failure(
                            errorMessage?.statusMessage ?? ""
                        )
                    }
                
                return result
            }
            .receive(on: DispatchQueue.main)
            .eraseToAnyPublisher()
    }
}

// MARK: - Dependencies
struct GetProfileUseCaseDependencies: HasSharedKMPManager {
    var sharedKMPManager: SharedKMPManagerType = SharedKMPManager.shared
}

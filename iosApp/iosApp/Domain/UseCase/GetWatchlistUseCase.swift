//
//  GetWatchlistUseCaseImpl.swift
//  iosApp
//
//  Created by rescalon on 6/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import Shared

// MARK: Protocol for DI
protocol HasGetWatchlistUseCase {
    var watchlistUseCase: GetWatchlistUseCaseType { get }
}

// MARK: UseCase Protocol
protocol GetWatchlistUseCaseType {
    func getWatchlist(sortBy: String, language: String) -> AnyPublisher<CustomNetworkResult<[Movie]>, Error>
}

// MARK: UseCase Implementation
struct GetWatchlistUseCase: GetWatchlistUseCaseType {
    
    // MARK: Dependencies
    typealias Dependencies = HasSharedKMPManager
    private let dependencies: Dependencies
    
    // MARK: Shared Instance
    static var shared = GetWatchlistUseCase(dependencies: GetWatchlistUseCaseDependencies())
    
    // MARK: Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
    }
    
    // MARK: - Combine function to fetch movies from the shared SDK.
    func getWatchlist(sortBy: String, language: String) -> AnyPublisher<CustomNetworkResult<[Movie]>, Error> {
        
        let future = createFuture(
            for: MoviesUseCaseProviderNativeKt.getWatchlistMovies(
                dependencies.sharedKMPManager.makeMoviesUseCaseProvider(),
                sortBy: sortBy,
                language: language
            )
        )
        
        return future
            .compactMap { networkResult in
                var result: CustomNetworkResult<[Movie]>? = nil
                
                // Map the NetworkResult from the shared KMP SDK.
                networkResult.onSuccess { data in
                    let movies = (data as? [SharedMovie])?.map { $0.toMovie() }
                    result = CustomNetworkResult.success(movies)
                }
                .onFailure { errorMessage in
                    result = CustomNetworkResult.failure(errorMessage?.statusMessage ?? "")
                }
                
                return result
            }
            .receive(on: DispatchQueue.main)
            .eraseToAnyPublisher()
    }
}

// MARK: GetWatchlistUseCase Dependencies
struct GetWatchlistUseCaseDependencies: HasSharedKMPManager {
    var sharedKMPManager: SharedKMPManagerType = SharedKMPManager.shared
}

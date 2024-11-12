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

class GetWatchlistUseCaseImpl: GetWatchlistUseCase {
    
    // MARK: - Provider
    let moviesUseCaseProvider: MoviesUseCaseProvider
    
    // MARK: - Init
    init(moviesUseCaseProvider: MoviesUseCaseProvider) {
        self.moviesUseCaseProvider = moviesUseCaseProvider
    }
    
    // MARK: - Combine function to fetch movies from the shared SDK.
    func execute(sortBy: String, language: String) -> AnyPublisher<CustomNetworkResult<[Movie]>, Error> {
        
        let future = createFuture(
            for: MoviesUseCaseProviderNativeKt.getWatchlistMovies(
                moviesUseCaseProvider, 
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

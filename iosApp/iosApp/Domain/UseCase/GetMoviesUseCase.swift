//
//  GetMoviesUseCase.swift
//  iosApp
//
//  Created by rescalon on 3/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import KMPNativeCoroutinesAsync
import Shared

// MARK: - Protocol for DI
protocol HasGetMoviesUseCase {
    var moviesUseCase: GetMoviesUseCaseType { get }
}

// MARK: - UseCase Protocol
protocol GetMoviesUseCaseType {
    func getMovies(
        category: String,
        page: Int,
        language: String
    ) async -> CustomNetworkResult<[Movie]>
}

// MARK: - UseCase Implementation
struct GetMoviesUseCase: GetMoviesUseCaseType {
    
    // MARK: Dependencies
    typealias Dependencies = HasSharedKMPManager
    private let dependencies: Dependencies
    
    // MARK: UseCase Instance
    static var shared = GetMoviesUseCase(dependencies: GetMoviesUseCaseDependencies())
    
    // MARK: Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
    }
    
    // MARK: Fetch movies by categories from the KMP SDK.
    func getMovies(category: String, page: Int, language: String) async -> CustomNetworkResult<[Movie]> {
        let asyncResult = await asyncResult(
            for: MoviesUseCaseProviderNativeKt.getMovies(
                dependencies.sharedKMPManager.makeMoviesUseCaseProvider(),
                category: category,
                page: Int32(page),
                language: language
            )
        )
        
        var result: CustomNetworkResult<[Movie]> = CustomNetworkResult.success([])
        switch asyncResult {
        case .success(let networkResult):
            networkResult
                .onSuccess { data in
                    let movies = (data as? [SharedMovie])?.map { $0.toMovie() }
                    result = CustomNetworkResult.success(movies)
                }
                .onFailure { errorMessage in
                    result = CustomNetworkResult.failure(errorMessage?.statusMessage ?? "")
                }
        case .failure(let error):
            result = CustomNetworkResult.failure(error.localizedDescription)
        }
        
        return result
    }
}

// MARK: - GetMoviesUseCase Dependencies
struct GetMoviesUseCaseDependencies: HasSharedKMPManager {
    var sharedKMPManager: SharedKMPManagerType = SharedKMPManager.shared
}

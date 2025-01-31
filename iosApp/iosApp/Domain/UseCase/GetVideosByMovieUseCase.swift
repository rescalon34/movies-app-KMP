//
//  GetVideosByMovieUseCase.swift
//  iosApp
//
//  Created by rescalon on 28/1/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import KMPNativeCoroutinesAsync
import Shared

// MARK: - Protocol for DI
protocol HasGetVideosByMovieUseCase {
    var videosByMovieUseCase: GetVideosByMovieUseCaseType { get }
}

// MARK: - UseCase Protocol
protocol GetVideosByMovieUseCaseType {
    func getVideosByMovies(movieId: Int) async -> Result<[Video], Error>
}

// MARK: - UseCase Implementation
struct GetVideosByMovieUseCase: GetVideosByMovieUseCaseType {
    
    // MARK: Dependencies
    typealias Dependencies = HasSharedKMPManager
    private let dependencies: Dependencies
    
    // MARK: UseCase Instance
    static var shared = GetVideosByMovieUseCase(dependencies: GetMoviesUseCaseDependencies())
    
    // MARK: Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
    }
    
    // MARK: Fetch videos by movie from the KMP SDK.
    func getVideosByMovies(movieId: Int) async -> Result<[Video], Error> {
        let asyncResult = await asyncResult(
            for: MoviesUseCaseProviderNativeKt.getVideosByMovie(
                dependencies.sharedKMPManager.makeMoviesUseCaseProvider(),
                movieId: Int32(movieId)
            )
        )
        
        var result: Result<[Video], Error> = Result.failure(NSError())
        switch asyncResult {
        case .success(let networkResult):
            networkResult
                .onSuccess { data in
                    let videos = (data as? [SharedVideo])?.map { $0.toVideo() } ?? []
                    result = Result.success(videos)
                }
                .onFailure { errorMessage in
                    result = Result.failure(NSError.error(message: errorMessage?.statusMessage ?? ""))
                }
        case .failure(let error):
            result = Result.failure(NSError.error(message: error.localizedDescription))
        }
        
        return result
    }
}

// MARK: - GetVideosByMovieUseCase Dependencies
struct GetVideosByMovieDependencies: HasSharedKMPManager {
    var sharedKMPManager: SharedKMPManagerType = SharedKMPManager.shared
}

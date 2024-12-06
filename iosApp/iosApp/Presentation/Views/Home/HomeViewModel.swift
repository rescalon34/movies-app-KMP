//
//  HomeViewModel.swift
//  iosApp
//
//  Created by rescalon on 3/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine

class HomeViewModel: ObservableObject {
    
    // MARK: - Dependencies
    typealias Dependencies = HasGetMoviesUseCase
    
    // MARK: - Published
    @Published var popularMovies: [Movie] = []
    @Published var nowPlayingMovies: [Movie] = []
    @Published var topRatedMovies: [Movie] = []
    @Published var errorMessage: String = ""
    @Published var isLoading: Bool = false
    
    // MARK: - Combine
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Properties
    private let dependencies: Dependencies
    
    // MARK: - Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
        Task { await getAsyncMovies() }
    }
    
    // MARK: - Fetching all movies asynchronously
    private func getAsyncMovies() async {
        self.isLoading = true
        
        async let popularMoviesRequest = getMoviesByCategory(MovieFilter.Popular.type)
        async let nowPlayingRequest = getMoviesByCategory(MovieFilter.NowPlaying.type)
        async let topRatedRequest = getMoviesByCategory(MovieFilter.TopRated.type)
        
        do {
            let (popular, nowPlaying, topRated) = try await(
                popularMoviesRequest,
                nowPlayingRequest,
                topRatedRequest
            )
            
            self.popularMovies = popular
            self.nowPlayingMovies = nowPlaying
            self.topRatedMovies = topRated
            
        } catch { self.errorMessage = error.localizedDescription }
        
        self.isLoading = false
    }
    
    // MARK: - Get movies by category.
    private func getMoviesByCategory(_ category: String) async throws -> [Movie] {
        let result = await dependencies
            .moviesUseCase
            .getMovies(
                category: category,
                page: ONE,
                language: LocalizationUtils.getCurrentLanguageCode()
            )
        
        switch result {
        case .success(let movies):
            return movies ?? []
        case .failure(let error):
            throw NSError.error(message: error)
        }
    }
}

// MARK: - Dependencies
struct HomeViewModelDependencies: HomeViewModel.Dependencies {
    var moviesUseCase: GetMoviesUseCaseType = GetMoviesUseCase.shared
}

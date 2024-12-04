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
    @Published var errorMessage: String = ""
    @Published var isLoading: Bool = false
    
    // MARK: - Combine
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Properties
    private let dependencies: Dependencies
    
    // MARK: - Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
        getMoviesByCategory(category: MovieFilter.Popular.type)
    }
    
    // MARK: - Fetch data from Network.
    func getMoviesByCategory(category: String) {
        self.isLoading = true
        
        dependencies
            .moviesUseCase
            .getMovies(
                category: category,
                page: ONE,
                language: LocalizationUtils.getCurrentLanguageCode()
            )
            .sink { _ in
                print("fetching movies by category: \(category)")
            } receiveValue: { [weak self] result in
                self?.isLoading = false
                switch result {
                case .success(let movies):
                    self?.popularMovies = movies ?? []
                case .failure(let error):
                    self?.errorMessage = error ?? ""
                }
            }
            .store(in: &cancellable)
    }
}

// MARK: - Dependencies
struct HomeViewModelDependencies: HomeViewModel.Dependencies {
    var moviesUseCase: GetMoviesUseCaseType = GetMoviesUseCase.shared
}

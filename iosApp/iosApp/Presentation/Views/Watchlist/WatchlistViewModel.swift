//
//  WatchlistViewModel.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Shared
import KMPNativeCoroutinesCombine
import KMPNativeCoroutinesAsync

class WatchlistViewModel: ObservableObject {
    
    // MARK: - Published properties
    @Published var isLoading: Bool = false
    @Published var movies: [Movie] = []
    @Published var errorMessage: String = ""
    @Published var sortType: String = SortType.FirstAdded.displayName
    @Published var sortOptions: [String] = SortType.allCases.map { $0.displayName }
    
    // MARK: - Combine properties
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Shared SDK
    let moviesUseCaseProvider: MoviesUseCaseProvider = SharedCoreManager.companion.getInstance()
        .createMoviesUseCaseProvider()
    
    // MARK: - init
    init() {
        getWatchlistMovies()
        setupBindings()
    }
    
    func getWatchlistMovies(sortBy: String = SortType.FirstAdded.displayName) {
        let language = LocalizationUtils.getCurrentLanguageCode()
        isLoading = true
        moviesUseCaseProvider.executeUseCase { manager, provider in
            Task { @MainActor [weak self] in
                try await asyncFunction(
                    for: manager.getWatchlistMovies(
                        provider,
                        sortBy: sortBy,
                        language: language
                    )
                )
                .onSuccess { data in
                    let movies = data as? [SharedMovie] ?? []
                    self?.movies = movies.map { $0.toMovie() }
                    self?.isLoading = false
                }
                .onFailure { errorMessage in
                    self?.errorMessage = errorMessage?.statusMessage ?? ""
                    self?.isLoading = false
                }
            }
        }
    }
    
    // MARK: - View functions.
    private func setupBindings() {
        onSelectedOptionChange()
    }
    
    func onSelectedOptionChange() {
        $sortType
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { option in
                let sortType = SortType.allCases.first { $0.displayName == option }?.sortType
                self.getWatchlistMovies(sortBy: sortType ?? SortType.FirstAdded.sortType)
            }
            .store(in: &cancellable)
    }
}

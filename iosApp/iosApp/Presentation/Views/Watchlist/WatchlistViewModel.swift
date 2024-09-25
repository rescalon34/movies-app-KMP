//
//  WatchlistViewModel.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared
import KMPNativeCoroutinesCombine
import KMPNativeCoroutinesAsync

class WatchlistViewModel: ObservableObject {
    
    // MARK: - Published properties
    @Published var isLoading: Bool = false
    @Published var movies: [Movie] = []
    @Published var errorMessage: String = ""
    @Published var sortType: String = SortType.FirstAdded.rawValue
    @Published var sortOptions: [String] = SortType.allCases.map { $0.rawValue }
    
    // MARK: - Combine properties
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Shared SDK
    let sharedCoreManager: SharedCoreManager = SharedCoreManager()
    
    // MARK: - init
    init() {
//        getWatchlist()
        getWatchlistMovies()
        setupBindings()
    }
    
    // MARK: - API calls thougth the shared SDK.
    func getWatchlist() {
        isLoading = true
        sharedCoreManager.useCaseProvider.executeUseCase { manager, provider in
            createPublisher(for: manager.getWatchlist(provider))
                .receive(on: DispatchQueue.main)
                .sink { [weak self] result in
                    switch result {
                    case .finished:
                        self?.isLoading = false
                    case .failure(let error):
                        self?.errorMessage = error.localizedDescription
                        self?.isLoading = false
                    }
                } receiveValue: { [weak self] movies in
                    self?.movies = movies
                }
                .store(in: &self.cancellable)
        }
    }
    
    func getWatchlistMovies() {
        isLoading = true
        sharedCoreManager.useCaseProvider.executeUseCase { manager, provider in
            Task { @MainActor [weak self] in
                try await asyncFunction(for: manager.getWatchlistMovies(provider))
                    .onSuccess { data in
                        let movies = data as? [Movie] ?? []
                        self?.movies = movies
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
                // TODO: execute API call to apply sorting feature
                print("Execute API call: \(option)")
            }
            .store(in: &cancellable)
    }
}

//
//  WatchlistViewModel.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import SwiftUI
import shared
import KMPNativeCoroutinesCombine

class WatchlistViewModel: ObservableObject {
    
    // MARK: - Published properties
    @Published var isLoading: Bool = false
    @Published var movies: [Movie] = []
    @Published var sortType: String = SortType.FirstAdded.rawValue
    @Published var sortOptions: [String] = SortType.allCases.map { $0.rawValue }
    
    // MARK: - Combine properties
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Shared SDK
    let sharedCoreManager: SharedCoreManager = SharedCoreManager()
    
    init() {
        getWatchlistMovies()
        setupBindings()
    }
    
    private func setupBindings() {
        onSelectedOptionChange()
    }
    
    // MARK: - get Watchlist using native coroutines + Combine
    func getWatchlistMovies() {
        isLoading = true
        sharedCoreManager.useCaseProvider.invoke { manager, provider in
            createPublisher(for: manager.getWatchlist(provider))
                .receive(on: DispatchQueue.main)
                .sink { [weak self] result in
                    switch result {
                    case .finished:
                        self?.isLoading = false
                        print("Finish!")
                    case .failure(let error):
                        print("An Error ocurred: \(error.localizedDescription)")
                    }
                } receiveValue: { [weak self] movies in
                    self?.movies = movies
                }
                .store(in: &self.cancellable)
        }
    }
    
    // This callback gets triggered when the selectedGenre changes, the `removeDuplicates()` function will avoid
    // executing again the other function to get movies.
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

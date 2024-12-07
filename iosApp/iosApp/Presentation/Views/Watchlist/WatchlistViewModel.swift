//
//  WatchlistViewModel.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation

class WatchlistViewModel: ObservableObject {
    
    // MARK: - Dependencies
    typealias Dependencies = HasGetWatchlistUseCase
    
    // MARK: - Published
    @Published var isLoading: Bool = false
    @Published var movies: [Movie] = []
    @Published var errorMessage: String? = nil
    @Published var sortType: String = SortType.FirstAdded.displayName
    @Published var sortOptions: [String] = SortType.allCases.map { $0.displayName }
    
    // MARK: - Combine
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Properties
    private let dependencies: Dependencies
    
    // MARK: - Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
        self.setupBindings()
        self.getWatchlistMovies()
    }
    
    // MARK: - Fetching data from network.
    func getWatchlistMovies(sortBy: String = SortType.FirstAdded.displayName) {
        self.isLoading = true
        
        dependencies
            .watchlistUseCase
            .getWatchlist(sortBy: sortBy,language: LocalizationUtils.getCurrentLanguageCode())
        .sink { _ in
            print("getting watchlist!")
        } receiveValue: { [weak self] response in
            self?.isLoading = false
            switch response {
            case .success(let movies):
                self?.movies = movies ?? []
            case .failure(let error):
                self?.errorMessage = error ?? ""
            }
        }
        .store(in: &cancellable)
    }
    
    // MARK: - View functions.
    private func setupBindings() {
        onSelectedOptionChange()
    }
    
    func onSelectedOptionChange() {
        $sortType
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] option in
                print("filter by this option: \(option)")
                let sortType = SortType.allCases.first { $0.displayName == option }?.sortType
                self?.getWatchlistMovies(sortBy: sortType ?? SortType.FirstAdded.sortType)
            }
            .store(in: &cancellable)
    }
}

// MARK: - Dependencies
struct WatchlistViewModelDependencies: WatchlistViewModel.Dependencies {
    var watchlistUseCase: GetWatchlistUseCaseType = GetWatchlistUseCase.shared
}

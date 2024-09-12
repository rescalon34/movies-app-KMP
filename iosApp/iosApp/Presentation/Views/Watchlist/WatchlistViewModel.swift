//
//  WatchlistViewModel.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

class WatchlistViewModel: ObservableObject {
    
    // MARK: - Published properties
    @Published var isLoading: Bool = false
    @Published var movies: [Movie] = []
    @Published var sortType: String = SortType.FirstAdded.rawValue
    @Published var sortOptions: [String] = SortType.allCases.map { $0.rawValue }
    
    // MARK: - Combine Properties
    private var cancellable = Set<AnyCancellable>()
    
    init() {
        getWatchlistMovies()
        setupBindings()
    }
    
    private func setupBindings() {
        onSelectedOptionChange()
    }
    
    // TODO: collect this asynchronously once the Shared Module handles the data correctly.
    func getWatchlistMovies() {
        isLoading = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 1.5) {
            self.isLoading = false
            self.movies = MovieRepositoryImpl().getWatchlist()
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

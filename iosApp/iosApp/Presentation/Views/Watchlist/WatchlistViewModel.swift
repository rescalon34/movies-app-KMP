//
//  WatchlistViewModel.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class WatchlistViewModel: ObservableObject {
    
    // MARK: - Published properties
    @Published var isLoading: Bool = false
    @Published var movies: [Movie] = []
    
    init() {
        getWatchlistMovies()
    }
    
    // TODO: collect this asynchronously once the Shared Module handles the data correctly.
    func getWatchlistMovies() {
        isLoading = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 1.5) {
            self.isLoading = false
            self.movies = MovieRepositoryImpl().getWatchlist()
        }
    }
}

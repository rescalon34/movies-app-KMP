//
//  MovieDetailViewModel.swift
//  iosApp
//
//  Created by rescalon on 20/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Combine
import Shared
import SwiftUI

class MovieDetailViewModel: ObservableObject {
    
    // MARK: - Published
    @Published var movie: Movie? = nil
    @Published var videosByMovie: [Movie] = []
    @Published var isLoading: Bool = false
    @Published var errorMessage: String = ""
    @Published var contentOffset: CGFloat = 0
    @Published var showNavigationTitle = false
    
    // MARK: - Combine
    private var cancellable = Set<AnyCancellable>()
    
    init() {
        getMovie()
    }
    
    // TODO: get real movie.
    func getMovie() {
        self.movie = MockedMoviesRepository.shared.getWatchlist().first?.toMovie()
    }
    
    // MARK: View functions
    func getMovieTitle() -> String {
        return movie?.title ?? ""
    }
    
    func onMinHeaderAppBarOffsetReached(value: CGFloat) {
        withAnimation(.easeInOut) {
            showNavigationTitle = value < MIN_APP_BAR_DETAILS_OFFSET
        }
    }
}

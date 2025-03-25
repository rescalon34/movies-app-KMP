//
//  MovieDetailViewModel.swift
//  iosApp
//
//  Created by rescalon on 20/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Combine
import SwiftUI

class MovieDetailViewModel: ObservableObject {
    
    // MARK: - Dependencies
    typealias Dependencies = HasGetVideosByMovieUseCase
    
    // MARK: - Published
    @Published var movie: Movie? = nil
    @Published var videosByMovie: [Video] = []
    @Published var isLoading: Bool = false
    @Published var errorMessage: String = ""
    @Published var contentOffset: CGFloat = 0
    @Published var showNavigationTitle = false
    @Published var isPlayerPresented = false
    
    // MARK: - Combine
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Properties
    private let dependencies: Dependencies
    
    // MARK: - Initializer
    init(movie: Movie?, dependencies: Dependencies) {
        self.dependencies = dependencies
        setMovieFromArgs(movie: movie)
        
        guard let movieId = movie?.id else { return }
        Task { await getVideosByMovie(movieId: movieId) }
    }
    
    // MARK: - Data fetch
    @MainActor
    private func getVideosByMovie(movieId: Int) async {
        self.isLoading = true
        
        let result = await dependencies
            .videosByMovieUseCase
            .getVideosByMovies(movieId: movieId)
        
        switch result {
        case .success(let videos):
            self.videosByMovie = videos
            self.isLoading = false
        case .failure(let error):
            self.isLoading = false
            self.errorMessage = error.localizedDescription
        }
    }
    
    // MARK: - View functions
    func onWatchTraillerClicked() {
        self.isPlayerPresented.toggle()
    }
    
    func getRandomVideo() -> Video? {
        self.videosByMovie.randomElement()
    }
    
    func setMovieFromArgs(movie: Movie?) {
        self.movie = movie
    }
    
    func getMovieTitle() -> String {
        return movie?.title ?? ""
    }
    
    func getReleaseDate() -> String {
        let label = "Released on: "
        guard let date = movie?.releaseDate?.formatReleaseYearAndMonth() else {
            return label + "N/A"
        }
        return label + date
    }
    
    func onMinHeaderAppBarOffsetReached(value: CGFloat) {
        withAnimation(.easeInOut) {
            showNavigationTitle = value < MIN_APP_BAR_DETAILS_OFFSET
        }
    }
}

// MARK: - Dependencies
struct MovieDetailViewModelDependencies: MovieDetailViewModel.Dependencies {
    var videosByMovieUseCase: GetVideosByMovieUseCaseType = GetVideosByMovieUseCase.shared
}

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
    typealias Dependencies = HasGetMoviesUseCase & HasGetVideosByMovieUseCase
    
    // MARK: - Published
    @Published var upcomingMovies: [Movie] = []
    @Published var popularMovies: [Movie] = []
    @Published var nowPlayingMovies: [Movie] = []
    @Published var topRatedMovies: [Movie] = []
    @Published var videosByMovie: [Video] = []
    @Published var errorMessage: String? = nil
    @Published var isLoading: Bool = false
    @Published var currentPagerItem: Int? = 0
    @Published var shouldAutoScroll: Bool = true
    @Published var isPlayerPresented = false
    @Published var selectedNowPlayMovie: Movie? = nil
    @Published var selectedMovieItem: Movie? = nil
    private var pagerScrollingTimer: AnyCancellable?
    
    // MARK: - Combine
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Properties
    private let dependencies: Dependencies
    
    // MARK: - Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
        Task { await getAsyncMovies() }
        setupBindings()
    }
    
    // MARK: - Networking functions
    
    /// Fetches all movie categories asynchronously.
    @MainActor
    private func getAsyncMovies() async {
        self.isLoading = true
        
        async let upcomingMoviesRequest = getMoviesByCategory(MovieFilter.Upcoming.type)
        async let popularMoviesRequest = getMoviesByCategory(MovieFilter.Popular.type)
        async let nowPlayingRequest = getMoviesByCategory(MovieFilter.NowPlaying.type)
        async let topRatedRequest = getMoviesByCategory(MovieFilter.TopRated.type)
        
        do {
            let (upcoming, popular, nowPlaying, topRated) = try await(
                upcomingMoviesRequest,
                popularMoviesRequest,
                nowPlayingRequest,
                topRatedRequest
            )
            
            self.upcomingMovies = upcoming
            self.popularMovies = popular
            self.nowPlayingMovies = nowPlaying
            self.topRatedMovies = topRated
            
        } catch { self.errorMessage = error.localizedDescription }
        
        self.isLoading = false
    }
    
    /// Fetches movies for a specific category.
    /// - Parameter category: The movie category.
    /// - Returns: A list of movies in the specified category.
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
            return movies
        case .failure(let error):
            throw NSError.error(message: error.localizedDescription)
        }
    }
    
    /// Fetches videos for a specific movie.
    /// - Parameter movieId: The ID of the movie.
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
            self.isPlayerPresented.toggle()
        case .failure(let error):
            self.isLoading = false
            self.errorMessage = error.localizedDescription
        }
    }

    // MARK: - UI functions
    /// Handles the click action for the "Play Now" movie item.
    /// - Parameter movie: The selected movie.
    func onPlayNowVideoClick(movie: Movie) {
        self.selectedNowPlayMovie = movie
        stopScrollingPager()
        Task { await getVideosByMovie(movieId: movie.id) }
    }
    
    func onMovieItemClick(movie: Movie) {
        self.selectedMovieItem = movie
    }
    
    /// Sets up the auto-scrolling pager timer to scroll every 3 seconds.
    private func setupAutoScrollingPagerTimer() {
        pagerScrollingTimer = Timer
            .publish(every: 3, on: .main, in: .common)
            .autoconnect()
            .sink(receiveValue: { [weak self] _ in
                self?.autoScrollToNextItem()
            })
    }
    
    /// Cancels the auto-scrolling pager timer.
    private func cancelAutoScrollingPagerTimer() {
        pagerScrollingTimer?.cancel()
        pagerScrollingTimer = nil
    }
    
    /// Scrolls the pager to the next item, looping back to the start position.
    private func autoScrollToNextItem() {
        guard let current = currentPagerItem, upcomingMovies.count > 0 else { return }
        // Loop back to the start after the last item
        
        let nextItem = (current + 1) % upcomingMovies.count
        print("Auto-scrolling to item: \(nextItem)")
        currentPagerItem = nextItem
    }
    
    /// Starts the pager auto-scrolling behavior.
    func startScrollingPager() {
        shouldAutoScroll = true
        setupAutoScrollingPagerTimer()
    }
    
    /// Stops the pager auto-scrolling behavior.
    func stopScrollingPager() {
        shouldAutoScroll = false
        cancelAutoScrollingPagerTimer()
    }
    
    /// Sets up bindings for auto-scrolling and other UI behaviors.
    private func setupBindings() {
        $currentPagerItem
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] _ in if self?.shouldAutoScroll == true { self?.startScrollingPager() } }
            .store(in: &cancellable)
        
        $shouldAutoScroll
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in $0 ? self?.startScrollingPager() : self?.stopScrollingPager() }
            .store(in: &cancellable)
        
        // Stop auto-scrolling when the video player is shown.
        $isPlayerPresented
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { $0 ? self.stopScrollingPager() : self.startScrollingPager() }
            .store(in: &cancellable)
    }
}

// MARK: - Dependencies
struct HomeViewModelDependencies: HomeViewModel.Dependencies {
    var moviesUseCase: GetMoviesUseCaseType = GetMoviesUseCase.shared
    var videosByMovieUseCase: GetVideosByMovieUseCaseType = GetVideosByMovieUseCase.shared
}

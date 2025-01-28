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
    @Published var upcomingMovies: [Movie] = []
    @Published var popularMovies: [Movie] = []
    @Published var nowPlayingMovies: [Movie] = []
    @Published var topRatedMovies: [Movie] = []
    @Published var errorMessage: String? = nil
    @Published var isLoading: Bool = false
    @Published var currentPagerItem: Int? = 0
    @Published var shouldAutoScroll: Bool = true
    @Published var isPlayerPresented = false
    @Published var selectedMovie: Movie? = nil
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
    
    // MARK: - Fetching all movies asynchronously
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
    
    // MARK: - Get movies by category.
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
    
    // MARK: - UI functions
    /// Setup AutoScrolling pager timer to publish an event every 3 seconds.
    private func setupAutoScrollingPagerTimer() {
        pagerScrollingTimer = Timer
            .publish(every: 3, on: .main, in: .common)
            .autoconnect()
            .sink(receiveValue: { [weak self] _ in
                self?.autoScrollToNextItem()
            })
    }
    
    /// Cancel AutoScrolling pager timer.
    private func cancelAutoScrollingPagerTimer() {
        pagerScrollingTimer?.cancel()
        pagerScrollingTimer = nil
    }
    
    /// Auto scroll to the next pager item, if the end is reached it will
    /// start over from the 0 position item.
    private func autoScrollToNextItem() {
        guard let current = currentPagerItem else { return }
        // Loop back to the start after the last item
        let nextItem = (current + 1) % upcomingMovies.count
        print("Auto-scrolling to item: \(nextItem)")
        currentPagerItem = nextItem
    }
    
    func startScrollingPager() {
        shouldAutoScroll = true
        setupAutoScrollingPagerTimer()
    }
    
    func stopScrollingPager() {
        shouldAutoScroll = false
        cancelAutoScrollingPagerTimer()
    }
    
    func onSelectedMovie(movie: Movie) {
        self.selectedMovie = movie
    }
    
    private func setupBindings() {
        // Listen for the current pager item to manage auto-scrolling.
        $currentPagerItem
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] _ in if self?.shouldAutoScroll == true { self?.startScrollingPager() } }
            .store(in: &cancellable)
        
        // Listen for auto-scrolling behavior based on the user's pressing state.
        $shouldAutoScroll
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in
                // if value is true, allow autoScrolling.
                $0 ? self?.startScrollingPager() : self?.stopScrollingPager()
            }
            .store(in: &cancellable)
    }
}

// MARK: - Dependencies
struct HomeViewModelDependencies: HomeViewModel.Dependencies {
    var moviesUseCase: GetMoviesUseCaseType = GetMoviesUseCase.shared
}

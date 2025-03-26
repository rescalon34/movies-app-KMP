//
//  HomeScreenView.swift
//  iosApp
//
//  Created by rescalon on 6/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreenView: View {
    
    // MARK: - Properties
    @StateObject var viewModel: HomeViewModel
    @Environment(\.scenePhase) var scenePhase
    
    // MARK: - Body
    var body: some View {
        NavigationStack {
            BaseScreenView(isLoading: viewModel.isLoading) {
                homeContent
            }
            .navigationTitle("Home")
            .navigationBarTitleDisplayMode(.automatic)
            .navigationDestination(isPresented: $viewModel.selectedMovieItem.toBinding()) {
                MovieDetailScreenView(
                    viewModel: .init(
                        movie: viewModel.selectedMovieItem,
                        dependencies: MovieDetailViewModelDependencies()
                    )
                )
            }
            .onChange(of: scenePhase, onScreenSceneChange)
            .sheet(isPresented: $viewModel.isPlayerPresented, content: showYouTubePlayer)
        }
    }
    
    // MARK: - Main screen content
    @ViewBuilder
    var homeContent: some View {
        if viewModel.errorMessage != nil {
            InfoMessageView(
                title: "An error occurred!",
                description: viewModel.errorMessage ?? ""
            )
        } else {
            if !viewModel.popularMovies.isEmpty {
                mainMoviesContent
                    .onAppear(perform: viewModel.startScrollingPager)
                    .onDisappear(perform: viewModel.stopScrollingPager)
            }
        }
    }
    
    // MARK: - Movies sections
    var mainMoviesContent: some View {
        GeometryReader { proxy in
            ScrollView {
                HorizontalPagerMoviesView(
                    movies: viewModel.upcomingMovies,
                    screenWidth: proxy.size.width,
                    currentPagerItem: $viewModel.currentPagerItem,
                    shouldAutoScroll: $viewModel.shouldAutoScroll
                )
                
                HorizontalMoviesSectionView(
                    title: MovieFilter.Popular.displayName,
                    movies: viewModel.popularMovies,
                    onMovieClicked: { viewModel.onMovieItemClick(movie: $0) }
                )
                
                HorizontalMoviesSectionView(
                    title: MovieFilter.NowPlaying.displayName,
                    movieItemSize: CGSizeMake(280, 160),
                    itemType: .Video,
                    movies: viewModel.nowPlayingMovies
                ) { movie in
                    viewModel.onPlayNowVideoClick(movie: movie)
                }
                
                HorizontalMoviesSectionView(
                    title: MovieFilter.TopRated.displayName,
                    movies: viewModel.topRatedMovies,
                    onMovieClicked: { viewModel.onMovieItemClick(movie: $0) }
                )
            }
            .scrollIndicators(.hidden)
        }
    }
    
    // MARK: - Functions
    private func onScreenSceneChange(oldValue: ScenePhase?, newValue: ScenePhase) {
        handleScenePhaseChanges(
            oldValue: oldValue,
            newValue: newValue,
            onActive: { viewModel.startScrollingPager() },
            onBackground: { viewModel.stopScrollingPager() }
        )
    }
    
    private func showYouTubePlayer() -> some View {
        YouTubePlayerView(
            title: viewModel.selectedNowPlayMovie?.title ?? "",
            videoKey: viewModel.videosByMovie.randomElement()?.key
        )
    }
}

// MARK: - Preview
#Preview {
    HomeScreenView(
        viewModel: .init(dependencies: HomeViewModelDependencies())
    )
}

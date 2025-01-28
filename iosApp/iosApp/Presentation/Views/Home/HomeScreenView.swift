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
    
    // MARK: - Body
    var body: some View {
        NavigationStack {
            BaseScreenView(isLoading: viewModel.isLoading) {
                homeContent
            }
            .navigationTitle("Home")
            .navigationBarTitleDisplayMode(.automatic)
            .sheet(isPresented: $viewModel.isPlayerPresented) {
                YouTubePlayerView(
                    title: viewModel.selectedMovie?.title ?? "",
                    videoKey: "nulvWqYUM8k"
                )
            }
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
                    onMovieClicked: { _ in }
                )
                
                HorizontalMoviesSectionView(
                    title: MovieFilter.NowPlaying.displayName,
                    movieItemSize: CGSizeMake(280, 160),
                    itemType: .Video,
                    movies: viewModel.nowPlayingMovies
                ) { movie in
                    viewModel.onSelectedMovie(movie: movie)
                    viewModel.isPlayerPresented.toggle()
                }
                
                HorizontalMoviesSectionView(
                    title: MovieFilter.TopRated.displayName,
                    movies: viewModel.topRatedMovies,
                    onMovieClicked: { _ in }
                )
            }
            .scrollIndicators(.hidden)
        }
    }
}

// MARK: - Preview
#Preview {
    HomeScreenView(
        viewModel: .init(dependencies: HomeViewModelDependencies())
    )
}

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
            }
        }
    }
    
    // MARK: - Movies sections
    var mainMoviesContent: some View {
        ScrollView {
            HorizontalMoviesSectionView(
                title: MovieFilter.Popular.displayName,
                movies: viewModel.popularMovies,
                onMovieClicked: { _ in }
            )
            
            HorizontalMoviesSectionView(
                title: MovieFilter.NowPlaying.displayName,
                movies: viewModel.nowPlayingMovies,
                onMovieClicked: { _ in }
            )
            
            HorizontalMoviesSectionView(
                title: MovieFilter.TopRated.displayName,
                movies: viewModel.topRatedMovies,
                onMovieClicked: { _ in }
            )
        }
        .scrollIndicators(.hidden)
    }
}

// MARK: - Preview
#Preview {
    HomeScreenView(
        viewModel: .init(dependencies: HomeViewModelDependencies())
    )
}

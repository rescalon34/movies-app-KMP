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
        if viewModel.errorMessage.isEmpty {
            ScrollView {
                mainMoviesContent
            }
            .scrollIndicators(.hidden)
        } else {
            errorMessage
        }
    }
    
    // MARK: - Movies sections
    @ViewBuilder
    var mainMoviesContent: some View {
        HorizontalMoviesSectionView(
            title: MovieFilter.Popular.displayName,
            movies: viewModel.popularMovies,
            onMovieClicked: { _ in }
        )
        
        HorizontalMoviesSectionView(
            title: MovieFilter.NowPlaying.displayName,
            movies: viewModel.popularMovies,
            onMovieClicked: { _ in }
        )
        
        HorizontalMoviesSectionView(
            title: MovieFilter.TopRated.displayName,
            movies: viewModel.popularMovies,
            onMovieClicked: { _ in }
        )
    }
    
    var errorMessage: some View {
        VStack {
            Text(viewModel.errorMessage)
                .multilineTextAlignment(.center)
        }
    }
}

// MARK: - Preview
#Preview {
    HomeScreenView(
        viewModel: .init(dependencies: HomeViewModelDependencies())
    )
}

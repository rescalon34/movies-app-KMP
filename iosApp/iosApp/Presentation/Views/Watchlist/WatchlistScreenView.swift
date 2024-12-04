//
//  WatchlistScreenView.swift
//  iosApp
//
//  Created by rescalon on 9/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct WatchlistScreenView: View {
    
    // MARK: - Properties
    @StateObject var viewModel: WatchlistViewModel
    @State private var isPresented = false
    
    // MARK: Body
    var body: some View {
        NavigationStack {
            BaseScreenView(isLoading: viewModel.isLoading) {
                mainContent
            }
            .navigationTitle("Watchlist")
            .navigationBarTitleDisplayMode(.automatic)
            .toolbar { watchlistToolbarContent }
            .fullScreenCover(isPresented: $isPresented) {
                SelectOptionScreenView(
                    options: viewModel.sortOptions,
                    selectedOption: $viewModel.sortType
                )
            }
        }
    }
    
    // MARK: - Content
    @ViewBuilder
    var mainContent: some View {
        if viewModel.errorMessage.isEmpty {
            ScrollView {
                VStack(alignment: .leading) {
                    watchlistGridContent
                }
                .padding()
            }
            .scrollIndicators(.hidden)
        } else {
            watchlistErrorMessage
        }
    }
    
    // MARK: - Views
    @ToolbarContentBuilder
    var watchlistToolbarContent: some ToolbarContent {
        ToolbarItem(placement: .topBarTrailing) {
            CategoryCardView(
                selectedCategory: viewModel.sortType,
                onCategoryClick: {
                    isPresented.toggle()
                }
            )
        }
    }
    
    var watchlistErrorMessage: some View {
        VStack {
            Text(viewModel.errorMessage)
                .multilineTextAlignment(.center)
        }
    }
    
    @ViewBuilder
    var watchlistGridContent: some View {
        Text("My movies")
            .font(.subheadline)
            .foregroundStyle(Color.customColors.secondaryTextColor)
            .bold()
        
        LazyVGridMoviesView(
            movies: viewModel.movies,
            movieItemSize: CGSize(width: 110, height: 250),
            lazyVGridColumns: 2,
            lazyVGridSpacing: (10, 10),
            onMovieClicked: { movie in
//                selectedMovie = movie
            }
        )
    }
}

#Preview {
    WatchlistScreenView(
        viewModel: .init(dependencies: WatchlistViewModelDependencies())
    )
}

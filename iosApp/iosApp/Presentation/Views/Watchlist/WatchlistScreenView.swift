//
//  WatchlistScreenView.swift
//  iosApp
//
//  Created by rescalon on 9/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct WatchlistScreenView: View {
    
    // MARK: - ViewModel
    @StateObject var viewModel: WatchlistViewModel = .init()
    
    // MARK: Body
    var body: some View {
        NavigationStack {
            BaseScreenView(isLoading: viewModel.isLoading) {
                mainContent
            }
            .navigationTitle("Watchlist")
            .navigationBarTitleDisplayMode(.automatic)
            .toolbar { watchlistToolbarContent }
        }
    }
    
    @ToolbarContentBuilder
    var watchlistToolbarContent: some ToolbarContent {
        ToolbarItem(placement: .topBarTrailing) {
            CategoryCardView(
                selectedCategory: "Featured",
                onCategoryClick: {
                    // TODO:
                    //isPresented.toggle()
                }
            )
        }
    }
    
    // MARK: - Views
    var mainContent: some View {
        ScrollView {
            VStack(alignment: .leading) {
                watchlistGridContent
            }
            .padding()
        }
        .scrollIndicators(.hidden)
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
    WatchlistScreenView()
}

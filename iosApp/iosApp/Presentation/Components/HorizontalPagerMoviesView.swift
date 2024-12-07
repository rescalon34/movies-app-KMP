//
//  HorizontalMoviesPagerView.swift
//  iosApp
//
//  Created by rescalon on 6/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

/// A horizontal pager view displaying a list of movies with a centered highlight effect.
struct HorizontalPagerMoviesView: View {
    
    // MARK: - Properties
    var movies: [Movie] = []
    var screenWidth: CGFloat
    
    // MARK: - Body
    var body: some View {
        BaseScreenView {
            pagerContent
        }
    }
    
    // MARK: - Main Horizontal pager content
    @ViewBuilder
    var pagerContent: some View {
        let pagerItemWidth = screenWidth * 0.85
        let sidePadding = (screenWidth - pagerItemWidth) / 2
        
        ScrollView(.horizontal) {
            HStack(spacing: 12) {
                ForEach(movies) { movie in
                    addPagerMovieItem(
                        movie: movie,
                        pagerItemWidth: pagerItemWidth
                    )
                }
            }
        }
        .contentMargins(.horizontal, sidePadding, for: .scrollContent)
        .scrollTargetLayout()
        .scrollTargetBehavior(.viewAligned)
        .scrollBounceBehavior(.basedOnSize)
        .scrollIndicators(.hidden)
    }
    
    // MARK: - Pager movie item
    @ViewBuilder
    func addPagerMovieItem(movie: Movie, pagerItemWidth: CGFloat) -> some View {
        Rectangle()
            .fill(Color.customColors.categoryCapsuleColor)
            .frame(width: pagerItemWidth, height: 420)
            .id(movie.id)
            .overlay {
                AsyncImageItemView(
                    imageUrl: movie.imageUrl,
                    movieItemSize: CGSize(width: 0, height: 420)
                )
            }
            .cornerRadius(8)
            .scrollTransition(.interactive) { content, phase in
                // Adjust opacity and scale for non-focused items.
                content
                    .opacity(phase.isIdentity ? 1 : 0.8)
                    .scaleEffect(y: phase.isIdentity ? 1 : 0.8)
            }
    }
}

#Preview {
    let movies = MockedMoviesRepository.shared.getWatchlist()
        .map { $0.toMovie() }
    return HorizontalPagerMoviesView(
        movies: movies,
        screenWidth: UIScreen.main.bounds.width
    )
}

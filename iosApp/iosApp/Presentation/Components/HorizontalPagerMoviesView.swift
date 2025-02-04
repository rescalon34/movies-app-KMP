//
//  HorizontalMoviesPagerView.swift
//  iosApp
//
//  Created by rescalon on 6/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

/// Displays a horizontal scrollable list of movies with a centered highlight effect.
struct HorizontalPagerMoviesView: View {
    
    // MARK: - Properties
    var movies: [Movie] = []
    var screenWidth: CGFloat
    @Binding var currentPagerItem: Int?
    @Binding var shouldAutoScroll: Bool
    
    // MARK: - Body
    var body: some View {
        BaseScreenView {
            VStack {
                pagerContent
            }
        }
    }
    
    // MARK: - Pager Content
    /// Displays the horizontal movie pager with aligned items.
    @ViewBuilder
    var pagerContent: some View {
        let pagerItemWidth = screenWidth * 0.85
        let sidePadding = (screenWidth - pagerItemWidth) / 2
        
        ScrollView(.horizontal) {
            LazyHStack(spacing: 12) {
                ForEach(movies, id: \.id) { movie in
                    addPagerMovieItem(
                        movie: movie,
                        pagerItemWidth: pagerItemWidth
                    )
                }
            }
            .scrollTargetLayout()
        }
        .scrollPosition(id: $currentPagerItem)
        .animation(.easeInOut, value: currentPagerItem)
        .contentMargins(.horizontal, sidePadding, for: .scrollContent)
        .scrollTargetBehavior(.viewAligned)
        .scrollBounceBehavior(.basedOnSize)
        .scrollIndicators(.hidden)
        .onEmptyTapGesture()
        .gesture(onPressGesture)
    }
    
    // MARK: - Pager movie item
    @ViewBuilder
    func addPagerMovieItem(movie: Movie, pagerItemWidth: CGFloat) -> some View {
        Rectangle()
            .fill(Color.customColors.categoryCapsuleColor)
            .frame(width: pagerItemWidth, height: 420)
            .id(movies.firstIndex(of: movie))
            .overlay {
                ZStack(alignment: .bottom) {
                    VStack {
                        AsyncImageItemView(
                            imageUrl: movie.imageUrl,
                            movieItemSize: CGSize(width: 0, height: 420),
                            imageResolution: ORIGINAL
                        )
                    }
                    
                    bottomGradientMask()
                    bottomPagerContent(movie.releaseDate)
                }
                
            }
            .cornerRadius(8)
            .scrollTransition(.animated(.interactiveSpring)) { content, phase in
                // Adjust opacity and scale for non-focused items.
                content
                    .opacity(phase.isIdentity ? 1 : 0.8)
                    .scaleEffect(y: phase.isIdentity ? 1 : 0.8)
            }
    }
    
    @ViewBuilder
    private func bottomPagerContent(_ releaseDate: String?) -> some View {
        HStack {
            Text(releaseDate?.formatReleaseYearAndMonth() ?? "")
            Spacer()
            Circle()
                .fill(shouldAutoScroll ? .green : .red)
                .frame(width: 8, height: 8, alignment: .bottomTrailing)
            
        }
        .padding()
    }
    
    // MARK: - Tracks onPressed Gesture
    /// Custom gesture to handle user interactions with the pager (onPress and onReleased).
    private var onPressGesture: some Gesture {
        DragGesture(minimumDistance: 0)
            .onChanged { _ in
                shouldAutoScroll = false
            }
            .onEnded { _ in
                shouldAutoScroll = true
            }
    }
}

#Preview {
    let movies = MockedMoviesRepository.shared.getWatchlist()
        .map { $0.toMovie() }
    return HorizontalPagerMoviesView(
        movies: movies,
        screenWidth: UIScreen.main.bounds.width,
        currentPagerItem: .constant(0),
        shouldAutoScroll: .constant(true)
    )
}

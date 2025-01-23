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
    @Binding var currentPagerItem: Int?
    
    // MARK: - Body
    var body: some View {
        BaseScreenView {
            VStack {
                pagerContent
                carouselButtons
            }
        }
    }
    
    // MARK: - Main Horizontal pager content
    @ViewBuilder
    var pagerContent: some View {
        let pagerItemWidth = screenWidth * 0.85
        let sidePadding = (screenWidth - pagerItemWidth) / 2
        
        ScrollViewReader { proxy in
            ScrollView(.horizontal) {
                LazyHStack(spacing: 12) {
                    ForEach(movies, id: \.id) { movie in
                        addPagerMovieItem(
                            movie: movie,
                            pagerItemWidth: pagerItemWidth
                        )
                    }
                }
                .onChange(of: currentPagerItem) { _, newValue in
                    DispatchQueue.main.asyncAfter(deadline: .now()) {
                        withAnimation(.easeInOut) {
                            proxy.scrollTo(newValue, anchor: .center)
                        }
                    }
                }
            }
            .scrollPosition(id: $currentPagerItem)
        }
        .contentMargins(.horizontal, sidePadding, for: .scrollContent)
        .scrollTargetLayout()
        .scrollTargetBehavior(.viewAligned)
        .scrollBounceBehavior(.basedOnSize)
        .scrollIndicators(.hidden)
    }
    
    private var carouselButtons: some View {
        HStack {
            Button("Previous") {
                withAnimation {
                    onScrollToPreviousItem()
                }
            }
            Spacer()
            Button("Next") {
                withAnimation {
                    onScrollToNextItem()
                }
            }
        }
        .padding(.horizontal)
        .padding(.top)
    }
    
    // MARK: - Pager movie item
    @ViewBuilder
    func addPagerMovieItem(movie: Movie, pagerItemWidth: CGFloat) -> some View {
        Rectangle()
            .fill(Color.customColors.categoryCapsuleColor)
            .frame(width: pagerItemWidth, height: 420)
            .id(movies.firstIndex(of: movie))
            .overlay {
                VStack {
                    AsyncImageItemView(
                        imageUrl: movie.imageUrl,
                        movieItemSize: CGSize(width: 0, height: 420)
                    )
                    // TODO: remove, this is just for testing purposes
                    Text("Position: \(currentPagerItem?.description ?? "")")
                        .padding(.bottom, 32)
                }
                
            }
            .cornerRadius(8)
            .scrollTransition(.animated) { content, phase in
                // Adjust opacity and scale for non-focused items.
                content
                    .opacity(phase.isIdentity ? 1 : 0.8)
                    .scaleEffect(y: phase.isIdentity ? 1 : 0.8)
                
            }
    }
    
    // MARK: - Functions
    private func onScrollToNextItem() {
        currentPagerItem = ((currentPagerItem ?? 0) + 1) % movies.count
        print("onScrollToNextItem(), index: \(String(describing: currentPagerItem?.description))")
    }
    
    private func onScrollToPreviousItem() {
        currentPagerItem = ((currentPagerItem ?? 0) - 1 + movies.count) % movies.count
        print("onScrollToPreviousItem(), index: \(String(describing: currentPagerItem?.description))")
    }
}

#Preview {
    let movies = MockedMoviesRepository.shared.getWatchlist()
        .map { $0.toMovie() }
    return HorizontalPagerMoviesView(
        movies: movies,
        screenWidth: UIScreen.main.bounds.width,
        currentPagerItem: .constant(0)
    )
}

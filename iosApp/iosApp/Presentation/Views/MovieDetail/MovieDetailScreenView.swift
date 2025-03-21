//
//  MovieDetailScreenView.swift
//  iosApp
//
//  Created by rescalon on 20/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MovieDetailScreenView: View {
    
    @StateObject var viewModel: MovieDetailViewModel = .init()
    
    // MARK: - Body
    var body: some View {
        BaseScreenView {
            movieDetailsContent
        }
        .navigationTitle(viewModel.showNavigationTitle ? viewModel.getMovieTitle() : "")
        .navigationBarTitleDisplayMode(.inline)
        .showToolbarBackground(isVisible: viewModel.showNavigationTitle)
        .onChange(of: viewModel.contentOffset, { _, newValue in
            viewModel.onMinHeaderAppBarOffsetReached(value: newValue)
        })
        .toolbar { detailToolbar }
    }
    
    // MARK: - Views
    private var movieDetailsContent: some View {
        ObservableScrollView(contentOffset: $viewModel.contentOffset) {
            VStack {
                if let movie = viewModel.movie {
                    movieDetailsHeader(movie: movie)
                } else {
                    Text("No content yet")
                }
            }
        }
        .translucentGradientMask(
            repeating: EIGTH,
            startPoint: .bottom,
            endPoint: .top
        )
        .edgesIgnoringSafeArea(.top)
    }
    
    // MARK: Toolbar
    @ToolbarContentBuilder
    private var detailToolbar: some ToolbarContent {
        ToolbarItem(placement: .topBarTrailing) {
            Button {
                // TODO: handle share logic.
            } label: {
                Image(systemName: "square.and.arrow.up")
                    .foregroundColor(.white)
            }
        }
    }
    
    // MARK: Header Image and overview.
    @ViewBuilder
    private func movieDetailsHeader(movie: Movie) -> some View {
        AsyncImageItemView(
            imageUrl: movie.imageUrl,
            movieItemSize: CGSize(width: 0, height: 520),
            imageResolution: ORIGINAL
        )
        .translucentGradientMask()
        .overlay {
            VStack(spacing: 4) {
                Spacer()
                Text(movie.title ?? "")
                    .foregroundColor(.white)
                    .font(.title)
                    .fontWeight(.heavy)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal)
                
                HStack {
                    Text(movie.releaseDate ?? "")
                        .foregroundColor(.white)
                        .font(.footnote)
                        .foregroundColor(Color.customColors.secondaryTextColor)
                        .padding(.horizontal)
                    Spacer()
                }
                .padding(.horizontal, 8)
            }
            .padding(.bottom, 24)
        }
    }
}

#Preview {
    MovieDetailScreenView()
}

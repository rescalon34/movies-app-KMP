//
//  MovieDetailScreenView.swift
//  iosApp
//
//  Created by rescalon on 20/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MovieDetailScreenView: View {
    
    @StateObject var viewModel: MovieDetailViewModel
    
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
                    detailOverview(movie: movie)
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
                    Text(viewModel.getReleaseDate())
                        .foregroundColor(.white)
                        .font(.footnote)
                        .foregroundColor(Color.customColors.secondaryTextColor)
                        .padding(.horizontal)
                    Spacer()
                }
                .padding(.horizontal, 8)
            }
        }
    }
    
    @ViewBuilder
    private func detailOverview(movie: Movie) -> some View {
        RoundedButtonView(
            btnText: "watch trailler".uppercased(),
            btnIcon: "play.fill",
            onClicked: {
                
            }
        )
        Text(movie.overview.orEmpty())
            .font(.callout)
            .frame(maxWidth: .infinity,alignment: .leading)
            .padding()
            .lineSpacing(4)
            .foregroundColor(Color.customColors.primaryClearTextColor)
    }
}

#Preview {
    let movie = SharedKMPManager.shared.makeMockedMovieRepository()
        .getWatchlist().first?.toMovie()
    
    return MovieDetailScreenView(viewModel: .init(movie: movie))
}

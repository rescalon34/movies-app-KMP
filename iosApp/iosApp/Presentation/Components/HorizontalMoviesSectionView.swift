//
//  HorizontalMoviesSectionView.swift
//  iosApp
//
//  Created by rescalon on 3/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HorizontalMoviesSectionView: View {
    
    // MARK: - Properties
    let title: String
    var movieItemSize: CGSize = CGSizeMake(110, 160)
    var itemType: MovieItemType = .Image
    let movies: [Movie]
    let onMovieClicked: (Movie) -> ()
    
    // MARK: - Body
    var body: some View {
        moviesSection
    }
    
    // MARK: - Horizontal section
    private var moviesSection: some View {
        VStack(alignment: .leading) {
            Text(title)
                .font(.subheadline)
                .foregroundStyle(Color.customColors.secondaryTextColor)
                .bold()
                .padding(.horizontal)
            
            ScrollView(.horizontal) {
                HStack {
                    ForEach(movies) { movie in
                        AsyncImageItemView(
                            imageUrl: movie.imageUrl,
                            movieItemSize: movieItemSize,
                            itemType: itemType
                        )
                        .onTapGesture {
                            onMovieClicked(movie)
                        }
                    }
                }
                .padding(.horizontal)
            }
            .scrollIndicators(.hidden)
        }
        .padding(.top)
    }
}

#Preview {
    HorizontalMoviesSectionView(
        title: "Popular",
        movies: [],
        onMovieClicked: { _ in }
    )
}

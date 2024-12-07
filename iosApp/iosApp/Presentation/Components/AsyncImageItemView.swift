//
//  MovieItemView.swift
//  MoviesApp
//
//  Created by rescalon on 7/6/24.
//

import SwiftUI

struct AsyncImageItemView: View {
    
    // MARK: - Properties
    let imageUrl: String?
    var movieItemSize: CGSize = CGSize(width: 110, height: 110)
    var itemType: MovieItemType = .Image
    
    // MARK: Body
    var body: some View {
        imageContainer
    }
    
    // MARK: - Views
    var imageContainer: some View {
        VStack {
            AsyncImage(url: URL(string: imageUrl.getSizedImage())) { image in
                image
                    .resizable()
                    .scaledToFill()
                    .frame(minWidth: movieItemSize.width, maxWidth: .infinity, minHeight: movieItemSize.height, maxHeight: movieItemSize.height)
                    .clipped()
                    .cornerRadius(8)
                    .overlay {
                        if itemType.type == MovieItemType.Video.type {
                            Color.black
                                .opacity(0.3)
                                .cornerRadius(8)
                            playOverlayIcon
                        }
                    }
                
            } placeholder: {
                Rectangle()
                    .fill(Color.customColors.categoryCapsuleColor)
                    .frame(minWidth: movieItemSize.width, maxWidth: .infinity, minHeight: movieItemSize.height, maxHeight: movieItemSize.height)
                    .clipped()
                    .cornerRadius(8)
            }
        }
    }
    
    var playOverlayIcon: some View {
        Image(systemName: "play.fill")
            .font(.caption)
            .padding()
            .background(
                Circle()
                    .fill(Color.white)
                    .frame(width: 44)
            )
            .foregroundStyle(.black)
    }
}

#Preview {
    AsyncImageItemView(imageUrl: DEFAULT_PLACEHOLDER_POSTER)
}

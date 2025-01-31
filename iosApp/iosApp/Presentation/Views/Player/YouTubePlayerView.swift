//
//  YouTubePlayerView.swift
//  iosApp
//
//  Created by rescalon on 27/1/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct YouTubePlayerView: View {
    
    // MARK: - Properties
    @Environment(\.dismiss) var dismissView
    let title: String
    let videoKey: String?
    
    // MARK: - Body
    var body: some View {
        NavigationStack {
            BaseScreenView {
                videoPlayerContent
            }
            .navigationTitle(title)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar { toolbarContent }
        }
    }
    
    // MARK: - Toolbar
    @ToolbarContentBuilder
    private var toolbarContent: some ToolbarContent {
        ToolbarItem(placement: .topBarTrailing) {
            Button("Done") {
                dismissView()
            }
            .accentColor(.blue)
        }
    }
    
    // MARK: - Views
    var videoPlayerContent: some View {
        let url = URL(string: videoKey?.getYoutubeVideoUrl() ?? "")
        
        guard let url = url else {
            // TODO: Show a proper view for content unavailable.
            return AnyView(Text("No URL provided"))
        }
        return AnyView(WebView(url: url))
    }
}

#Preview {
    NavigationStack {
        YouTubePlayerView(
            title: "Movie",
            videoKey: SharedKMPManager.shared.sharedCoreManager
                .getMockedRepository()
                .getMockedVideoKey()
        )
    }
}

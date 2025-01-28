//
//  BottomTabView.swift
//  iosApp
//
//  Created by rescalon on 9/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

/// This Bottom TabView contains all the menu options available.
struct BottomTabView: View {
    @State private var selectedTabItem : BottomTabItem = .Home
    
    var body: some View {
        bottomTabView
    }
    
    // MARK: - BottomTabView items.
    var bottomTabView: some View {
        TabView(selection: $selectedTabItem) {
            HomeScreenView(viewModel: .init(dependencies: HomeViewModelDependencies()))
                .tabItem { Image(systemName: "house.fill") }
                .tag(BottomTabItem.Home)
            
            WatchlistScreenView(viewModel: .init(dependencies: WatchlistViewModelDependencies()))
                .tabItem { Image(systemName: "plus.circle.fill") }
                .tag(BottomTabItem.Watchlist)
            
            ProfileScreenView(viewModel: .init(dependencies: ProfileViewModelDependencies()))
                .tabItem { Image(systemName: "person.crop.circle.fill") }
                .tag(BottomTabItem.Profile)
        }
    }
}

#Preview {
    BottomTabView()
}

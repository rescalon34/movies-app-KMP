//
//  WatchlistScreenView.swift
//  iosApp
//
//  Created by rescalon on 9/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct WatchlistScreenView: View {
    // MARK: Body
    var body: some View {
        BaseScreenView {
            mainContent
        }
    }
    
    // MARK: - Views
    var mainContent: some View {
        VStack {
            Text("Watchlist Screen")
        }
    }
}

#Preview {
    WatchlistScreenView()
}

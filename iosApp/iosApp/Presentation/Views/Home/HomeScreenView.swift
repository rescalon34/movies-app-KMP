//
//  HomeScreenView.swift
//  iosApp
//
//  Created by rescalon on 6/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreenView: View {
    
    // MARK: Body
    var body: some View {
        BaseScreenView {
            homeContent
        }
    }
    
    // MARK: - Views
    var homeContent: some View {
        VStack {
            Text("Home Screen")
        }
    }
}

// MARK: - Preview
#Preview {
    HomeScreenView()
}

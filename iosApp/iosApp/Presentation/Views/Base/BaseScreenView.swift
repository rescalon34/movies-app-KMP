//
//  BaseScreenView.swift
//  iosApp
//
//  Created by rescalon on 6/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

/// A reusable screen content to reuse the background and loading components on every screen.
struct BaseScreenView<Content: View>: View {
    
    var isLoading: Bool = false
    let content: () -> Content
    
    var body: some View {
        ZStack {
            Color.customColors.backgroundColor
                .ignoresSafeArea()
            content()
            if isLoading { ProgressView() }
        }
    }
}

#Preview {
    BaseScreenView(isLoading: true) {
        Text("Base screen")
    }
}

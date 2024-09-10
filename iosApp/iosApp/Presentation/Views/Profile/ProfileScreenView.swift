//
//  ProfileScreenView.swift
//  iosApp
//
//  Created by rescalon on 9/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProfileScreenView: View {
    // MARK: Body
    var body: some View {
        BaseScreenView {
            mainContent
        }
    }
    
    // MARK: - Views
    var mainContent: some View {
        VStack {
            Text("Profile Screen")
        }
    }
}

#Preview {
    ProfileScreenView()
}

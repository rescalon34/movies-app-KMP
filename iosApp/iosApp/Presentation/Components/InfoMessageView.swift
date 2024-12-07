//
//  InfoMessageView.swift
//  iosApp
//
//  Created by rescalon on 5/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct InfoMessageView: View {
    
    var title: String = "Coming Soon!"
    var description: String = "This feature is currently being developed. Check back soon!"
    
    var body: some View {
        VStack {
            Text(title)
                .padding(.horizontal)
            
            if !description.isEmpty {
                Text(description)
                    .padding(.horizontal, 24)
                    .padding(.top, 4)
                    .multilineTextAlignment(.center)
            }
        }
    }
}

#Preview {
    InfoMessageView()
}

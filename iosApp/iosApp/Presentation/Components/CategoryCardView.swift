//
//  CategoryCardView.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CategoryCardView: View {
    
    let selectedCategory: String
    let onCategoryClick: () -> ()
    
    var body: some View {
        HStack {
            Group {
                Text(selectedCategory)
                Image(systemName: "chevron.down")
            }
            .font(.subheadline)
            .foregroundColor(.primary)
        }
        .lineLimit(1)
        .foregroundColor(.white)
        .padding(.horizontal)
        .padding(.vertical, 8)
        .background(
            Capsule()
                .fill(Color.customColors.categoryCapsuleLigthColor)
        )
        .onTapGesture {
            onCategoryClick()
        }
    }
}

#Preview {
    CategoryCardView(
        selectedCategory: "Featured",
        onCategoryClick: {}
    )
}

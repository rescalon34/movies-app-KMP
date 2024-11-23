//
//  SquaredShapeModifier.swift
//  iosApp
//
//  Created by rescalon on 21/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct RoundedBorderModifier: ViewModifier {
    
    func body(content: Content) -> some View {
        content
            .padding()
            .padding(.horizontal, 24)
            .background(Color.gray.opacity(0.4))
            .clipShape(RoundedRectangle(cornerRadius: 10))
    }
}

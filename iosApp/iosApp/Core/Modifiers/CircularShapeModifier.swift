//
//  ViewModifiers.swift
//  iosApp
//
//  Created by rescalon on 21/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CircularShapeModifier: ViewModifier {
    
    func body(content: Content) -> some View {
        content
            .clipShape(Circle())
            .shadow(radius: 5)
    }
}

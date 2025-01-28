//
//  View+Extensions.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension View {
    
    /// This function creates a flexible GridItem list based on the given columns number.
    /// - Param columns: the number of columns that the LazyVGrid will display.
    /// - hSpacing: the horizontal space between each flexible grid items.
    ///
    func getFlexibleGridColumns(_ columns: Int, _ hSpacing: CGFloat) -> [GridItem] {
        return Array(repeatElement(GridItem(.flexible(), spacing: hSpacing), count: columns))
    }
    
    /// represents the horizontal and vertical spaces of a LazyVGrid
    typealias LazyVGridSpacing = (horizontal: CGFloat, vertical: CGFloat)
    
    /// A View modifier that applies a translucent effect while scrolling through items.
    ///
    /// This function creates a mask effect of an `opaque scrolled item at the top and bottom`
    /// of the ScrollView while vertically scrolling items.
    ///
    func fadedScrollViewMask() -> some View {
        mask(
            LinearGradient(
                colors: [
                    .clear,
                    .primary,
                    .primary,
                    .clear,
                ],
                startPoint: .top, 
                endPoint: .bottom
            )
        )
    }
    
    func bottomGradientMask() -> some View {
        LinearGradient(
            gradient: Gradient(colors: [Color.black.opacity(0.8), Color.clear]),
            startPoint: .bottom,
            endPoint: .top
        )
        .frame(height: 100)
        .allowsHitTesting(false)
    }
    
    func circularGradientOverlay() -> some View {
        Circle().stroke(
            AngularGradient(
                gradient: Gradient(colors: [Color.purple, Color.mint, Color.indigo]), center: .leading
            ),
            lineWidth: 3
        )
    }
    
    func circularShape() -> some View {
            self.modifier(CircularShapeModifier())
        }
    
    func roundedBorderShape() -> some View {
        self.modifier(RoundedBorderModifier())
    }
    
    /// Custom gesture to handle user interactions with the pager.
    /// This onTapGesture works as an easy workaround for handling Gestures with ScrollView.
    /// See here for more: https://danielsaidi.com/blog/2022/11/16/using-complex-gestures-in-a-scroll-view
    func onEmptyTapGesture() -> some View {
        onTapGesture { }
    }
}

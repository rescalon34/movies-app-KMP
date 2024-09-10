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
}

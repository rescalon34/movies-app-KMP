//
//  Binding+Extensions.swift
//  iosApp
//
//  Created by rescalon on 20/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

/// Add Binding Extensions.
extension Binding {
    
    /// Converts a `Binding<Optional>` to a `Binding<Bool>`.
    ///
    /// This function isolates the logic to get and set the value,
    /// allowing the UI to simply call `$myValue.toBinding()` and
    /// get a `Binding<Bool>` based on whether the optional value
    /// is `nil` or not.
    ///
    /// - Returns: A `Binding<Bool>` that is `true` if the optional value is not `nil`.
    ///
    /// The `set:` closure clears the selected item value by setting it to `nil` when the binding is set to `false`
    func toBinding<T>() -> Binding<Bool> where Value == T? {
        return Binding<Bool>(
            get: { self.wrappedValue != nil },
            set: { newValue in
                if !newValue {
                    self.wrappedValue = nil
                }
            }
        )
    }
}

//
//  ViewHelper.swift
//  iosApp
//
//  Created by rescalon on 28/1/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

/// Handles app lifecycle changes by responding to `ScenePhase` updates.
/// It will detect when the app goes to background and returns to foreground.
///
/// - Parameters:
///   - oldValue: The previous ScenePhase, used for tracking transitions.
///   - newValue: The current ScenePhase, provided by the `onChange` modifier.
///   - onActive: Action to perform when the app becomes active.
///   - onInactive: Action to perform when the app becomes inactive.
///   - onBackground: Action to perform when the app moves to the background.
///
func handleScenePhaseChanges(
    oldValue: ScenePhase?,
    newValue: ScenePhase,
    onActive: () -> () = {},
    onInactive: () -> Void = {},
    onBackground: () -> () = {}
) {
    switch newValue {
    case .active:
        print("phase: active")
        onActive()
    case .inactive:
        onInactive()
        print("phase: inactive")
    case .background:
        print("phase: background")
        onBackground()
    @unknown default:
        print("phase: unknonw phase")
    }
}

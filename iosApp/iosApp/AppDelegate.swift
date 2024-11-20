//
//  AppDelegate.swift
//  iosApp
//
//  Created by rescalon on 20/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    
    typealias Dependencies = HasSharedKMPManager
    private let dependencies = AppDelegateDependencies()
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        setupSharedSdk()
        return true
    }
    
    /// Initialize the Shared KMP SDK, pass your own apiKey and accessToken to the SDK initialization
    /// to make API requests.
    private func setupSharedSdk() {
        dependencies.sharedKMPManager.sharedCoreManager.doInit()
    }
}

// MARK: AppDelegate Dependencies
struct AppDelegateDependencies: AppDelegate.Dependencies {
    var sharedKMPManager: SharedKMPManagerType = SharedKMPManager.shared
}

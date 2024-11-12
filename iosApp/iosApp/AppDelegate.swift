//
//  AppDelegate.swift
//  iosApp
//
//  Created by rescalon on 20/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        initSharedSdk()
        return true
    }
    
    /// Initialize the Shared KMP SDK, pass your own apiKey and accessToken to the SDK initialization
    /// to make API requests.
    private func initSharedSdk() {
        SharedDependencyContainer.shared.sharedCoreManager.doInit()
    }
}

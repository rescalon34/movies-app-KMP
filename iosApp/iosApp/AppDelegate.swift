//
//  AppDelegate.swift
//  iosApp
//
//  Created by rescalon on 20/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        SharedCoreManager.companion.getInstance().doInit()
        
        return true
    }
}

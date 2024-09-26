//
//  String+Extensions.swift
//  iosApp
//
//  Created by rescalon on 25/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension String? {
    
    /// Extension function to provide a handy access to the Native Kotlin Extension function.
    /// - Parameter imageSize: the desired image resolution, it fallbacks to the default poster size.
    ///
    func getSizedImage(_ imageSize: String = SharedSDKExtensionKt.DEFAULT_POSTER_SIZE) -> String {
        SharedSDKExtensionKt.getSizedImage(
            self ?? "", 
            imageSize: imageSize
        )
    }
}

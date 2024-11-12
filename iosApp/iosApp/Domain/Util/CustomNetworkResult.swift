//
//  CustomNetworkResult.swift
//  iosApp
//
//  Created by rescalon on 7/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

/// Custom result type to handle network responses from Kotlin Multiplatform in a Swift-friendly way.
///
/// `CustomNetworkResult<T>` is designed to map network results from Kotlin Multiplatform (KMP) to
/// a Swift-native result type. This allows Swift code to work with network results more intuitively,
/// improving readability and reducing compatibility issues between Kotlin and Swift.
///
/// - `success(T?)`: A successful network response with optional data of type `T`.
/// - `failure(String?)`: A failure in the network request, containing an optional error message.
enum CustomNetworkResult<T> {
    case success(T?)
    case failure(String?)
}

//
//  SharedCoreManager+Extensions.swift
//  iosApp
//
//  Created by rescalon on 20/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import KMPNativeCoroutinesCore

///
/// This Extension file provide access to handy functions to invoke any Kotlin useCase from the
/// Shared KMP module with less code using a closure approach.
///
extension UseCaseProvider {
    
    /// Extension function to easily invoke any useCase in a handy way.
    ///
    /// Example:
    ///   ```
    ///   let getWatchlist = sharedCoreManager.useCaseProvider.invoke { manager, provider in
    ///       manager.getWatchlist(provider)
    ///   }
    ///   ```
    ///
    func invoke<T>(_ useCase: @escaping (UseCaseProviderNativeKt.Type, UseCaseProvider) -> T) -> T {
        return useCase(UseCaseProviderNativeKt.self, self)
    }
    
    /// Extension function to easily invoke any useCase in a handy way.
    ///
    /// Example:
    ///   ```
    ///   let getWatchlist = sharedCoreManager.useCaseProvider.invoke { manager, provider in
    ///       createPublisher(for: manager.getWatchlist(provider))
    ///       // rest of the code.
    ///   }
    ///   ```
    ///
    func invoke(_ useCase: (UseCaseProviderNativeKt.Type, UseCaseProvider) -> ()) -> () {
        return useCase(UseCaseProviderNativeKt.self, self)
    }
}

//
//  SharedUseCaseProvider+Extensions.swift
//  iosApp
//
//  Created by rescalon on 20/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Shared
import KMPNativeCoroutinesCore

/// This extension provides convenient functions to simplify the process of invoking Native Kotlin usecases
/// from the shared Kotlin Multiplatform (KMP) module with minimal code, using closures.
///
extension SharedUseCaseProvider {
    
    /// Fetches data from any Kotlin Multiplatform (KMP) use case in a concise and efficient way.
    ///
    /// Example usage:
    ///   ```
    ///   let data = useCaseProvider.fetchUseCase { manager, provider in
    ///       manager.getData(provider)
    ///   }
    ///   ```
    /// - Parameter useCase: Closure to execute the desired use case.
    /// - Returns: The result from the Native Kotlin usecase.
    func fetchUseCase<T>(_ useCase: @escaping (SharedUseCaseProviderNativeKt.Type, SharedUseCaseProvider) -> T) -> T {
        useCase(SharedUseCaseProviderNativeKt.self, self)
    }
    
    /// Executes a Kotlin Multiplatform (KMP) usecase without returning data.
    ///
    /// Example:
    ///   ```
    ///   useCaseProvider.executeUseCase { manager, provider in
    ///       createPublisher(for: manager.getData(provider))
    ///   }
    ///   ```
    /// - Parameter useCase: Closure to execute the desired use case.
    func executeUseCase(_ useCase: (SharedUseCaseProviderNativeKt.Type, SharedUseCaseProvider) -> ()) -> () {
        useCase(SharedUseCaseProviderNativeKt.self, self)
    }
}

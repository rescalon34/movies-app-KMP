//
//  ProfileViewModel.swift
//  iosApp
//
//  Created by rescalon on 21/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine

class ProfileViewModel: ObservableObject {
    
    // MARK: - Dependencies
    typealias Dependencies = HasGetProfileUseCase
    
    // MARK: - Published
    @Published var isLoading: Bool = false
    @Published var profile: Profile? = nil
    @Published var errorMessage: String? = nil
    
    // MARK: Combine
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - Properties
    private let dependencies: Dependencies
    
    // MARK: Initializer
    init(dependencies: Dependencies) {
        self.dependencies = dependencies
        self.getProfile()
    }
    
    // MARK: Fetch profile
    func getProfile() {
        self.isLoading = true
        
        dependencies
            .getProfileUseCase
            .getProfile()
            .sink { _ in
                print("got profile!")
            } receiveValue: { [weak self] result in
                self?.isLoading = false
                
                switch result {
                case .success(let profile):
                    self?.profile = profile
                case .failure(let error):
                    self?.errorMessage = error ?? ""
                }
            }
            .store(in: &cancellable)
    }
}

// MARK: Dependencies
struct ProfileViewModelDependencies: ProfileViewModel.Dependencies {
    var getProfileUseCase: GetProfileUseCaseType = GetProfileUseCase.shared
}

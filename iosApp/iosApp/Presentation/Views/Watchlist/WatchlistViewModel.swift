//
//  WatchlistViewModel.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation

class WatchlistViewModel: ObservableObject {
    
    // MARK: - UseCase properties
    let getWatchlistUseCase: GetWatchlistUseCase
    
    // MARK: - Published properties
    @Published var isLoading: Bool = false
    @Published var movies: [Movie] = []
    @Published var errorMessage: String = ""
    @Published var sortType: String = SortType.FirstAdded.displayName
    @Published var sortOptions: [String] = SortType.allCases.map { $0.displayName }
    
    // MARK: - Combine properties
    private var cancellable = Set<AnyCancellable>()
    
    // MARK: - init
    init(getWatchlistUseCase: GetWatchlistUseCase) {
        self.getWatchlistUseCase = getWatchlistUseCase
        self.setupBindings()
        self.getWatchlistMovies()
    }
    
    // MARK: - UseCase functions
    func getWatchlistMovies(sortBy: String = SortType.FirstAdded.displayName) {
        self.isLoading = true
        
        getWatchlistUseCase.execute(
            sortBy: sortBy,
            language: LocalizationUtils.getCurrentLanguageCode()
        )
        .sink { _ in
            print("getting watchlist!")
        } receiveValue: { [weak self] response in
            self?.isLoading = false
            switch response {
            case .success(let movies):
                self?.movies = movies ?? []
            case .failure(let error):
                self?.errorMessage = error ?? ""
            }
        }
        .store(in: &cancellable)
    }
    
    // MARK: - View functions.
    private func setupBindings() {
        onSelectedOptionChange()
    }
    
    func onSelectedOptionChange() {
        $sortType
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] option in
                print("filter by this option: \(option)")
                let sortType = SortType.allCases.first { $0.displayName == option }?.sortType
                self?.getWatchlistMovies(sortBy: sortType ?? SortType.FirstAdded.sortType)
            }
            .store(in: &cancellable)
    }
}

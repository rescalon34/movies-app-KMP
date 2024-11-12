//
//  GetWatchlistMoviesUseCase.swift
//  iosApp
//
//  Created by rescalon on 6/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine

protocol GetWatchlistUseCase {
    
    func execute(sortBy: String, language: String) -> AnyPublisher<CustomNetworkResult<[Movie]>, Error>
}

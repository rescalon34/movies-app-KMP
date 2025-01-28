//
//  Movie+Extensions.swift
//  iosApp
//
//  Created by rescalon on 23/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Shared

extension SharedMovie {
    func toMovie() -> Movie {
        Movie(
            id: Int(id),
            title: title,
            imageUrl: imageUrl,
            releaseDate: releaseDate
        )
    }
}

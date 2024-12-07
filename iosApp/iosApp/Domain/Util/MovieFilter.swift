//
//  MovieFilter.swift
//  iosApp
//
//  Created by rescalon on 3/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum MovieFilter {
    case NowPlaying
    case Popular
    case TopRated
    case Upcoming
    
    var displayName: String {
        switch self {
        case .NowPlaying: "Now Playing"
        case .Popular: "Popular"
        case .TopRated: "Top Rated"
        case .Upcoming: "Upcoming"
        }
    }
    
    var type: String {
        switch self {
        case .NowPlaying: "now_playing"
        case .Popular: "popular"
        case .TopRated: "top_rated"
        case .Upcoming: "upcoming"
        }
    }
}

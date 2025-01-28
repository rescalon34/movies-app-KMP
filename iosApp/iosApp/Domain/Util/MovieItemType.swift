//
//  MovieItemType.swift
//  iosApp
//
//  Created by rescalon on 5/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum MovieItemType {
    case Video
    case Image
    
    var type: String {
        switch self {
        case .Video: "video"
        case .Image: "image"
        }
    }
}

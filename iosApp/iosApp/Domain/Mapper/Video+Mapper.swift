//
//  Video+Mapper.swift
//  iosApp
//
//  Created by rescalon on 28/1/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Shared

extension SharedVideo {
    func toVideo() -> Video {
        Video(
            id: id,
            name: name,
            key: key
        )
    }
}

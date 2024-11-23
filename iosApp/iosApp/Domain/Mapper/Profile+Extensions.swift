//
//  Profile+Extensions.swift
//  iosApp
//
//  Created by rescalon on 21/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

extension SharedProfile {
    func toProfile() -> Profile {
        Profile(
            userName: self.username,
            imageUrl: self.imageUrl
        )
    }
}

//
//  NsError+Extensions.swift
//  iosApp
//
//  Created by rescalon on 5/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

extension NSError {
    static func error(message: String?) -> NSError {
        return NSError(
            domain: "",
            code: 0,
            userInfo: [NSLocalizedDescriptionKey: message ?? ""]
        )
    }
}

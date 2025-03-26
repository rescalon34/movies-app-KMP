//
//  OptionalString+Extension.swift
//  iosApp
//
//  Created by rescalon on 21/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

extension Optional where Wrapped == String {
    func orEmpty() -> String {
        return self ?? ""
    }
}

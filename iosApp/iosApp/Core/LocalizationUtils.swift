//
//  LocalizationUtils.swift
//  iosApp
//
//  Created by rescalon on 9/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct LocalizationUtils {
    static func getCurrentLanguageCode() -> String {
        guard let language = Locale.preferredLanguages.first else {
            return "en-US"
        }
        
        let locale = Locale(identifier: language)
        let languageCode = locale.language.languageCode?.identifier ?? ""
        let regionCode = locale.region?.identifier ?? Locale.current.region?.identifier ?? "US"
        
        return "\(languageCode)-\(regionCode)"
    }
}

//
//  FilterKeyword.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum SortType: String, CaseIterable, Identifiable {
    case FirstAdded = "First Added"
    case LastAdded = "Last Added"
    
    var id: String { return self.rawValue }
}

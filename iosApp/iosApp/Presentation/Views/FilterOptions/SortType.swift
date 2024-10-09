//
//  FilterKeyword.swift
//  iosApp
//
//  Created by rescalon on 10/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum SortType: CaseIterable, Identifiable {
    case FirstAdded
    case LastAdded
    
    var displayName: String {
        switch self {
        case .FirstAdded: "First Added"
        case .LastAdded: "Last Added"
        }
    }
    
    var sortType: String {
        switch self {
        case .FirstAdded: "created_at.asc"
        case .LastAdded: "created_at.desc"
        }
    }
    
    var id: String { return self.displayName }
}

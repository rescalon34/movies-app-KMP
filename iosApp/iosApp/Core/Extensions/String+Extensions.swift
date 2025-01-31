//
//  String+Extensions.swift
//  iosApp
//
//  Created by rescalon on 27/1/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

extension String {
    func formatReleaseYearAndMonth() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = YEAR_MONTH_DAY_FORMAT
        
        guard let date = dateFormatter.date(from: self) else { return "" }
        
        dateFormatter.dateFormat = MONTH_FORMAT
        let month = dateFormatter.string(from: date)
        
        let calendar = Calendar.current
        let year = calendar.component(.year, from: date)
        
        return "\(month) \(year)"
    }
    
    func getYoutubeVideoUrl() -> String {
        return BASE_YOUTUBE_URL + self
    }
}

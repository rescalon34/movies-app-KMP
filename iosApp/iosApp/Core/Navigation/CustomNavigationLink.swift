//
//  CustomNavigationLink.swift
//  iosApp
//
//  Created by rescalon on 21/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CustomNavigationLink<Destination: View, Label: View>: View {
    var destination: Destination
    var label: () -> Label
    
    var body: some View {
        NavigationLink(destination: destination) {
            label()
        }
        .listRowBackground(Color.customColors.backgroundColor)
    }
}

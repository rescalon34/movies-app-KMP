//
//  RoundedButtonView.swift
//  iosApp
//
//  Created by rescalon on 21/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct RoundedButtonView: View {
    
    let btnText: String
    let btnIcon: String
    let onClicked: () -> ()
    
    var body: some View {
        buttonContent
    }
    
    private var buttonContent: some View {
        Label(btnText, systemImage: btnIcon)
            .frame(maxWidth: .infinity)
            .padding(.vertical, 12)
            .background(Color.customColors.accentColor)
            .foregroundColor(Color.customColors.backgroundColor)
            .bold()
            .font(.callout)
            .clipShape(RoundedRectangle(cornerRadius: 6))
            .padding(.horizontal)
            .padding(.top)
            .onTapGesture(perform: onClicked)
    }
}

#Preview {
    RoundedButtonView(
        btnText: "PLAY",
        btnIcon: "play.fill",
        onClicked: {}
    )
}

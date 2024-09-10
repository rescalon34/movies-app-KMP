//
//  BackgroundBlurView.swift
//  MoviesApp
//
//  Created by rescalon on 10/6/24.
//

import SwiftUI

/// Custom Background BlurView.
///
struct BackgroundBlurView: UIViewRepresentable {
    
    var style: UIBlurEffect.Style = .systemThinMaterialDark
    
    func makeUIView(context: Context) -> UIView {
        let view = UIVisualEffectView(effect: UIBlurEffect(style: style))
        DispatchQueue.main.async {
            view.superview?.superview?.backgroundColor = .clear
        }
        return view
    }

    func updateUIView(_ uiView: UIView, context: Context) {}
}

#Preview {
    BackgroundBlurView()
        .ignoresSafeArea()
}

//
//  WebView.swift
//  iosApp
//
//  Created by rescalon on 27/1/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import WebKit

/// A reusable view to load any URL within a webView.
struct WebView: UIViewRepresentable {
    let url: URL
    var isScrollEnabled: Bool = true
    
    func makeUIView(context: Context) -> some WKWebView {
        WKWebView()
    }
    
    func updateUIView(_ webView: UIViewType, context: Context) {
        let request = URLRequest(url: url)
        webView.scrollView.isScrollEnabled = isScrollEnabled
        webView.load(request)
    }
}

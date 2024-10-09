//
//  MovieFilterFullScreenView.swift
//  MoviesApp
//
//  Created by rescalon on 10/6/24.
//

import SwiftUI

/// This view displays a list of given strings in a generic filter screen.
///
struct SelectOptionScreenView: View {
    
    // MARK: - Properties
    @Environment(\.dismiss) var dismissView
    @State var rotationDegrees: CGFloat = 5
    let options: [String]
    @Binding var selectedOption: String
    
    // MARK: - Body
    var body: some View {
        VStack {
            mainContent
            dismissIcon
        }
        .frame(maxWidth: .infinity)
        .background(
            BackgroundBlurView()
                .ignoresSafeArea()
        )
        .onAppear {
            animateXMarkIcon(degrees: CGFloat(MINUS_NINETY_DEGREES))
        }
    }
    
    // MARK: - Views
    private var mainContent: some View {
        ScrollView {
            ScrollViewReader { proxy in
                VStack(spacing: 40) {
                    extraScrollSpacer()
                    ForEach(options, id: \.self) { option in
                        optionTextItem(option: option)
                            .onAppear {
                                scrollToSelectedOption(proxy: proxy)
                            }
                            .onTapGesture {
                                selectedOption = option
                                onDismiss()
                            }
                    }
                    extraScrollSpacer()
                }
            }
        }
        .scrollIndicators(.hidden)
        .fadedScrollViewMask()
    }
    
    // MARK: View Functions
    @ViewBuilder
    private func optionTextItem(option: String) -> some View {
        let isSelectedOption = selectedOption == option
        
        Text(option)
            .id(option)
            .frame(maxWidth: .infinity)
            .foregroundColor(.white)
            .font(isSelectedOption ? .headline : .subheadline)
            .fontWeight(isSelectedOption ? .bold : .light)
    }
    
    /// This extra spacer gives a nice scrolling effect at the top and bottom
    /// of the ScrollView while scrolling categories.
    private func extraScrollSpacer() -> some View {
        Spacer().frame(height: 120)
    }
    
    private var dismissIcon: some View {
        CircleIconView(iconName: "xmark")
            .onTapGesture {
                onDismiss()
            }
            .rotationEffect(.degrees(rotationDegrees))
            .padding(.vertical, 32)
    }
    
    private func onDismiss() {
        animateXMarkIcon(degrees: 0)
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            dismissView()
        }
    }
    
    // MARK: Animation Functions
    private func animateXMarkIcon(degrees: CGFloat) {
        withAnimation(.easeInOut.delay(0.1)) {
            rotationDegrees = degrees
        }
    }
    
    private func scrollToSelectedOption(proxy: ScrollViewProxy) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            withAnimation(.easeInOut) {
                proxy.scrollTo(selectedOption, anchor: .center)
            }
        }
    }
}

// MARK: - Preview
#Preview {
    BaseScreenView {
        SelectOptionScreenView(
            options: SortType.allCases.map({ $0.displayName }),
            selectedOption: .constant(SortType.FirstAdded.displayName)
        )
    }
}

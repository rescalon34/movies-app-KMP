//
//  ProfileScreenView.swift
//  iosApp
//
//  Created by rescalon on 9/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProfileScreenView: View {
    
    // MARK: Properties
    @StateObject var viewModel: ProfileViewModel
    
    // MARK: Body
    var body: some View {
        NavigationStack {
            BaseScreenView(isLoading: viewModel.isLoading) {
                profileContent
            }
            .navigationTitle("Profile")
        }
    }
    
    // MARK: - Content
    var profileContent: some View {
        GeometryReader { geo in
            ScrollView {
                profileHeader
                makeSettingsOptions(parentHeight: geo.size.height)
            }
            .scrollIndicators(.hidden)
        }
    }
    
    // MARK: Header
    @ViewBuilder
    var profileHeader: some View {
        VStack {
            AsyncImageItemView(
                imageUrl: viewModel.profile?.imageUrl ?? "",
                movieItemSize: CGSize(width: 150, height: 150)
            )
            .frame(maxWidth: 150)
            
            .circularShape()
            .overlay {
                circularGradientOverlay()
            }
            
            Text(viewModel.profile?.userName ?? "")
                .padding(.vertical, 4)
            
            Text("Edit Profile")
                .padding(.horizontal, 32)
                .roundedBorderShape()
        }
        .frame(maxWidth: .infinity)
    }
    
    // MARK: - Settings options
    func makeSettingsOptions(parentHeight: CGFloat) -> some View {
        List {
            CustomNavigationLink(destination: ComingSoonView()) {
                profileOptionItem(option: "App Settings")
            }
            CustomNavigationLink(destination: ComingSoonView()) {
                profileOptionItem(option: "Account policy")
            }
            CustomNavigationLink(destination: ComingSoonView()) {
                profileOptionItem(option: "Privacy policy")
            }
            CustomNavigationLink(destination: ComingSoonView()) {
                profileOptionItem(option: "Credits")
            }
            CustomNavigationLink(destination: ComingSoonView()) {
                profileOptionItem(option: "Logout")
            }
            Text("Version 1.0.0")
                .listRowBackground(Color.customColors.backgroundColor)
                .padding(.vertical, 10)
        }
        .listStyle(.plain)
        .scrollDisabled(true)
        .frame(height: parentHeight / 1.5)
    }
    
    // MARK: - Functions
    private func profileOptionItem(option: String) -> some View {
        Text(option)
            .font(.callout)
            .bold()
            .listRowBackground(Color.customColors.backgroundColor)
            .padding(.vertical, 10)
    }
}

#Preview {
    ProfileScreenView(
        viewModel: .init(dependencies: ProfileViewModelDependencies())
    )
}

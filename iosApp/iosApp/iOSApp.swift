import SwiftUI

@main
struct iOSApp: App {

    private let dependencyContainer: DependencyContainer
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    init() {
        dependencyContainer = .shared
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView(dependencyContainer: dependencyContainer)
		}
	}
}

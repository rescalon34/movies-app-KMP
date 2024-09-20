import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        SharedCoreManager.companion.doInit()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

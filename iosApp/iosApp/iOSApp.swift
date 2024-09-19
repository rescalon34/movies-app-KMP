import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        SharedCoreManager().doInitKoinModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

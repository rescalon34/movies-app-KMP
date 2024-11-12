import SwiftUI

struct ContentView: View {
    
    let dependencyContainer: DependencyContainer
        
	var body: some View {
        BottomTabView(dependencyContainer: dependencyContainer)
	}
}

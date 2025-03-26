# 🎬 Movies App KMP

A cross-platform movie application built with Kotlin Multiplatform (KMP) for shared business logic and fully native declarative UIs using **Jetpack Compose** (Android) and **SwiftUI** (iOS).

---

## 🚀 Project Overview

This Kotlin Multiplatform project demonstrates how to share core business logic—such as networking and data models—across Android and iOS platforms, while building fully native UIs for each. The project is structured as a **mono-repo** for simplicity, but is modularized to allow clean separation of responsibilities and platform-specific implementation details.

**The app is inspired by the Disney+ and Star+ apps, using **The Movie Database (TMDB) API** to fetch and display movie content.**

---

## ✨ Features

- Horizontal carousel of **Upcoming** movies on the Home screen.
- Display of **Popular**, **Now Playing**, and **Top Rated** movies in horizontal sections.
- **Watchlist** screen with filter options.
- **Profile** screen with static user info and actions.
- **Details** screen with image header, movie overview, and the ability to play a YouTube trailer via an embedded WebView.
- Interactive and animated UI using **Jetpack Compose** and **SwiftUI**.

---

## ⚙️ Technical Overview

This project leverages **Kotlin Multiplatform** to share core logic across Android and iOS. Networking and domain logic reside in the shared module, which is seamlessly consumed by Swift through [KMP-NativeCoroutines](https://github.com/rickclephas/KMP-NativeCoroutines), enabling compatibility with `async/await`, `Combine`, and `AsyncSequence`.

**The UI layers for Android and iOS are fully native, enabling a great user experience while maximizing shared business logic.**

---

## 🧩 Tech Stack

### 🔗 Networking & Shared Module

- [Kotlin Multiplatform Documentation](https://kotlinlang.org/docs/multiplatform.html)
- [Ktor (Multiplatform HTTP client)](https://ktor.io/docs/client-create-multiplatform-application.html)
- [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html)
- [Ktorfit (Retrofit-like wrapper for Ktor)](https://foso.github.io/Ktorfit/)
- [Ktor Client Darwin (iOS)](https://api.ktor.io/ktor-client/ktor-client-darwin/)
- [Ktor Client OkHttp (Android)](https://api.ktor.io/ktor-client/ktor-client-okhttp/)
- [Kotlin Multiplatform Plugin](https://kotlinlang.org/docs/multiplatform.html)
- [KMP-NativeCoroutines](https://github.com/rickclephas/KMP-NativeCoroutines)

### 📱 Android-Specific

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt for Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)
- Type-safe navigation using [Compose Navigation DSL](https://developer.android.com/guide/navigation/design/type-safety)

### 🍎 iOS-Specific

- [SwiftUI](https://developer.apple.com/xcode/swiftui/)
- Protocol-based manual dependency injection inspired by [Swift with Majid](https://swiftwithmajid.com/2019/03/06/dependency-injection-in-swift-with-protocols/)
- Integration with KMP using Swift Concurrency features

---

## 🎨 Screenshots

### 📱 Android

#### 🧭 Main Screens

| Carousel | Home | Profile |
|----------|------|---------|
| <img src="https://github.com/user-attachments/assets/f8b84b18-1ad6-4d92-85f9-23a2f6cbb50a" width="300"/> | <img src="https://github.com/user-attachments/assets/689a3b02-f322-4980-91da-647f9886d93d" width="300"/> | <img src="https://github.com/user-attachments/assets/bfdf42ed-8748-4732-8d2c-83b351e662f1" width="300"/> |

#### 📂 Other Screens

| Watchlist | Filter | Detail |
|-----------|--------|--------|
| <img src="https://github.com/user-attachments/assets/d2b0eb21-04f6-4e12-a2bf-ee8e946db9e6" width="300"/> | <img src="https://github.com/user-attachments/assets/b7e77d76-4276-4d5c-b3a8-3cb99489a65b" width="300"/> | <img src="https://github.com/user-attachments/assets/a66f10c4-c2a3-4f36-8554-e04eb6c5a772" width="300"/> |

---

### 🍎 iOS

#### 🧭 Main Screens

| Carousel | Home | Profile |
|----------|------|---------|
| <img src="https://github.com/user-attachments/assets/af6b73db-c296-4add-b98c-059742a18fe8" width="300"/> | <img src="https://github.com/user-attachments/assets/81a77968-a5f1-4b52-a21c-76ce2d3a34ef" width="300"/> | <img src="https://github.com/user-attachments/assets/b2a5c1a9-c177-4fe6-b7f1-8acbdfd60d48" width="300"/> |

#### 📂 Other Screens

| Watchlist | Filter | Detail |
|-----------|--------|--------|
| <img src="https://github.com/user-attachments/assets/52723ebe-e981-439c-9eb0-e23aada2929c" width="300"/> | <img src="https://github.com/user-attachments/assets/4bffce60-70b3-4df4-b9b8-0a6a6fd64c20" width="300"/> | <img src="https://github.com/user-attachments/assets/019a9c21-0496-42c9-9bf3-4dd6060c7666" width="300"/> |

---

## 🧪 Project Setup

To initialize the shared logic provided by the `SharedCoreManager` from the **Shared module**, follow these steps depending on your platform. This setup allows the shared SDK to perform authenticated API requests to [TMDB](https://www.themoviedb.org/).

### 🔧 Android Setup

Initialize the SDK inside your custom `Application` class. Inject the `SharedCoreManager` and call `init()` passing your TMDB credentials.

```kotlin
@HiltAndroidApp
class MoviesAndroidApplication : Application() {

    @Inject
    lateinit var sharedCoreManager: SharedCoreManager

    override fun onCreate() {
        super.onCreate()
        sharedCoreManager.init(
            accessToken = YOUR_ACCESS_TOKEN,
            apiKey = YOUR_API_KEY
        )
    }
}
```
### 🔧 iOS Setup

Inside your AppDelegate.swift, initialize the shared SDK by calling doInit() on the SharedCoreManager instance.
```Swift
class AppDelegate: NSObject, UIApplicationDelegate {
    
    typealias Dependencies = HasSharedKMPManager
    private let dependencies = AppDelegateDependencies()
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        setupSharedSdk()
        return true
    }
    
    /// Initialize the Shared KMP SDK, pass your own apiKey and accessToken to the SDK initialization
    /// to make API requests.
    private func setupSharedSdk() {
        dependencies
            .sharedKMPManager
            .sharedCoreManager.doInit(
                apiKey: "YOUR_API_KEY",
                accessToken: "YOUR_ACCESS_TOKEN"
            )
    }
}

```
⚠️ **Notes**: 
- Ensure the `Shared` module is properly integrated in your iOS project using Xcode and the Kotlin Multiplatform configuration.
- Ensure to get an `ACCESS_TOKEN` and `API_KEY` from your TMDB account.

## License
This project is licensed under the Apache 2.0 License.

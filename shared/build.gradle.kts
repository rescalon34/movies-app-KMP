plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.devtoolsKsp)
    alias(libs.plugins.nativeCoroutines)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {

    // This will allow KDcos comments to be translated to readable text when
    // compiled to Objective-C.
    // NOTE: According to Jetbrains, this feature is experimental and may be removed (or not)
    // in the future, keep an eye on its status.

    // @See: https://kotlinlang.org/docs/native-objc-interop.html#provide-documentation-with-kdoc-comments
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.bundles.common.ktor.impl)
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "com.escalondev.movies_app_kmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Exposed Koin Dependency for the Android consumer to have access to
    // the wrapped: `GlobalContext.get()` from the `getKoinGlobalContext()` function.
    api(libs.koin.core)
}

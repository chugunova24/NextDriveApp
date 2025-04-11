plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.example.nextdrive"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nextdrive"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true  // Включаем поддержку Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get() // Получаем версию из TOML
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
//    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Compose
    implementation(libs.androidx.compose.ui)  // Для Compose UI
    implementation(libs.androidx.compose.ui.tooling) // Для Preview
    implementation(libs.androidx.compose.material)  // Для Material Design в Compose
    implementation(libs.androidx.compose.foundation)  // Для Compose Foundation
    implementation(libs.androidx.compose.activity)  // Для activity-compose
    implementation(libs.androidx.compose.navigation)  // Для навигации Compose

    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha10")

    implementation("androidx.compose.material:material-icons-extended:1.6.1")
    implementation("io.coil-kt:coil-compose:2.1.0")

    implementation("com.google.accompanist:accompanist-pager:0.28.0")

    implementation(platform("io.github.jan-tennert.supabase:bom:3.1.3"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.1.4")
    implementation("io.ktor:ktor-client-android:3.1.0")
    implementation("io.github.jan-tennert.supabase:realtime-kt")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.6.1")
    implementation("io.github.jan-tennert.supabase:auth-kt:3.1.3")
    implementation("io.github.jan-tennert.supabase:supabase-kt:3.1.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")

    implementation("io.coil-kt:coil-compose:2.6.0")

    // To use the Animator APIs
    implementation("androidx.core:core-animation:1.0.0")
    // To test the Animator APIs
    androidTestImplementation("androidx.core:core-animation-testing:1.0.0")
    // Optional - To enable APIs that query the performance characteristics of GMS devices.
    implementation("androidx.core:core-performance:1.0.0")
    // Optional - to use ShortcutManagerCompat to donate shortcuts to be used by Google
    implementation("androidx.core:core-google-shortcuts:1.1.0")
    // Optional - to support backwards compatibility of RemoteViews
    implementation("androidx.core:core-remoteviews:1.1.0")
    // Optional - APIs for SplashScreen, including compatibility helpers on devices prior Android 12
    implementation("androidx.core:core-splashscreen:1.2.0-beta01")

    //Koin new version
//    implementation("io.insert-koin:koin-android:4.0.2")
    // Java Compatibility
//    implementation("io.insert-koin:koin-android-compat:4.0.2")
    // App Startup
//    implementation("io.insert-koin:koin-androidx-startup:4.0.2")
//    implementation("koin-androidx-compose:4.0.2")
//    implementation("koin-androidx-compose-navigation:4.0.2")

    implementation(libs.koin.compose)

    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("io.github.jan-tennert.supabase:storage-kt:3.1.4")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")

}
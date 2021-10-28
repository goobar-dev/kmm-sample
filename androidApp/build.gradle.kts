plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")

    implementation("androidx.compose.compiler:compiler:1.0.4")
    implementation("androidx.compose.ui:ui:1.0.4")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.4")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.4")
    // Material Design
    implementation("androidx.compose.material:material:1.0.4")
    // Activity extenstions for Compose
    implementation("androidx.activity:activity-compose:1.4.0")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "dev.goobar.kmm_sample.android"
        minSdk = 23
        targetSdk = 31
        compileSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.4"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}
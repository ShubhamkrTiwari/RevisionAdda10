plugins {
    id("com.android.application")
    kotlin("android")
}

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.shubhamkrtiwari.revisionadda"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.2")

    // Added dependencies requested in improve/ui-and-theme branch
    implementation("androidx.datastore:datastore-preferences:1.1.0")
    implementation("io.coil-kt:coil-compose:2.3.0")
}

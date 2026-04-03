plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.a6_7lablotwindow"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.a6_7lablotwindow"
        minSdk = 23
        targetSdk = 36
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
        // Основная библиотека для интерфейса и Activity
        implementation("androidx.appcompat:appcompat:1.6.1")
        // Библиотека для дизайна (кнопки, списки)
        implementation("com.google.android.material:material:1.9.0")
        // Для ConstraintLayout, если используешь его
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
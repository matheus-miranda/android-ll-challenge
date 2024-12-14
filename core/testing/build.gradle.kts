plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.luizalabs.testing"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android)

    api(libs.junit)
    testImplementation(libs.room.testing)
    api(libs.coroutines.test)
    api(libs.mockk)

    api(libs.androidx.junit)
    api(libs.arch.testing)
    api(libs.navigation.compose.testing)
    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui.test.junit4)
    api(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    api(libs.androidx.espresso.core)
}

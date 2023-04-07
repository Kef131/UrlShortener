plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nubank.urlshortener"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.nubank.urlshortener"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_9
        targetCompatibility = JavaVersion.VERSION_1_9
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("com.google.dagger:hilt-android:2.44")
    implementation("org.chromium.net:cronet-api:98.4758.101")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    implementation(libs.androidx.databinding)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    implementation(libs.squareup.retrofit.rxjava)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraint)
    implementation(libs.androidx.activity)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.tests)
}

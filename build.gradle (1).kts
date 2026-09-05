plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.megcup.signalbot"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.megcup.signalbot"
        minSdk = 26
        targetSdk = 35
        versionCode = 4
        versionName = "4.0.0"
        buildConfigField("String", "APP_VERSION", "\"4.0.0\"")
        buildConfigField("Boolean", "QUOTEX_OFFICIAL_API_ENABLED", "false")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.01.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.activity:activity-compose:1.10.0")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.navigation:navigation-compose:2.8.6")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
}

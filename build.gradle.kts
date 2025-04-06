plugins {
    id("com.android.application") version "8.9.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("org.jetbrains.kotlin.kapt") version "2.0.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
    id("androidx.room") version "2.7.0-rc03" apply false // ✅ Add this line
}

// Optional safety net (useful for older Gradle behavior)
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id 'com.android.library'
    id 'kotlin-android'
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdkVersion = 31
    buildToolsVersion = "31.0.0"

    defaultConfig {
        minSdkVersion 21
        targetSdkVersion = 31
        versionName "1.0"
        versionCode 1
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = '1.8'
    }
}
import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.secrets.gradle.plugin)
}

android {
    namespace = "com.christianto.natalio.android.lab"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.christianto.natalio.android.lab"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    fun Packaging.() {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.retrofit2)
    implementation(libs.com.google.dagger.hilt.android)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.okhttp)
    implementation(libs.io.coil.kt.compose)
    annotationProcessor(libs.room.compiler)
    ksp(libs.com.google.dagger.hilt.compiler)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx)
    debugImplementation(libs.bundles.androidx)
}
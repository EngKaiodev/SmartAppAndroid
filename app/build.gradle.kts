import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.kotlin.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.smartapp"
    compileSdk = 35

    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localProperties.load(localPropertiesFile.inputStream())
    }

    defaultConfig {
        applicationId = "com.example.smartapp"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "LOCAL_HOST_IP",
            "\"${localProperties["LOCAL_HOST_IP"] ?: "127.0.0.1"}\""
        )
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.androidx.core.ktx)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    androidTestImplementation(libs.room.testing)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.rxjava2)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    compileOnly(libs.lombok)

    // Retrofit + RxJava
    implementation(libs.adapter.rxjava2)
    implementation(libs.logging.interceptor)

    // RxJava
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    // -----------------------
    // Firebase
    // -----------------------
    // Firebase BoM: gerencia versões das libs Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))

    // Firebase Cloud Messaging
    implementation("com.google.firebase:firebase-messaging")

    // Firebase Analytics e utilitários KTX
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-common-ktx")
}
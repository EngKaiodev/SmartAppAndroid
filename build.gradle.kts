plugins {
    alias(libs.plugins.navigation.safe.args) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.application) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dagger.hilt.android) apply false

    // Plugin Google Services para Firebase
    id("com.google.gms.google-services") version "4.4.3" apply false
}

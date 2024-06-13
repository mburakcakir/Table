// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin.jetbrains) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.android.compose.compiler) apply false
}
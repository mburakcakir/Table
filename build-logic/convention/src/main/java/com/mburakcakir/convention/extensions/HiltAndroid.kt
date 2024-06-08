package com.mburakcakir.convention.extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltAndroid() {
    pluginManager.apply(libs.plugins.android.hilt.get().pluginId)

    dependencies {
        implementation(libs.hilt.android)
        implementation(libs.hilt.navigation.compose)
        ksp(libs.hilt.android.compiler)
    }
}
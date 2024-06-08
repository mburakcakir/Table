package com.mburakcakir.convention.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    pluginManager.apply {
        apply(libs.plugins.android.kotlin.jetbrains.get().pluginId)
        apply(libs.plugins.kotlin.ksp.get().pluginId)
    }
    with(commonExtension) {
        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            compileSdk = libs.versions.compileSdk.get().toInt()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}
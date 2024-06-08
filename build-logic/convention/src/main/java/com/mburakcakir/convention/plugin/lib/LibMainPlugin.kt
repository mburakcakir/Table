package com.mburakcakir.convention.plugin.lib

import com.android.build.api.dsl.LibraryExtension
import com.mburakcakir.convention.extensions.androidTestImplementation
import com.mburakcakir.convention.extensions.configureKotlinAndroid
import com.mburakcakir.convention.extensions.implementation
import com.mburakcakir.convention.extensions.libs
import com.mburakcakir.convention.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class LibMainPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugins.android.library.get().pluginId)

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    lint.targetSdk = libs.versions.targetSdk.get().toIntOrNull()
                }
                configureKotlinAndroid(this)
            }

            dependencies {
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.appcompat)

                testImplementation(libs.junit)
                androidTestImplementation(libs.androidx.junit)
                androidTestImplementation(libs.androidx.espresso.core)
            }
        }
    }
}
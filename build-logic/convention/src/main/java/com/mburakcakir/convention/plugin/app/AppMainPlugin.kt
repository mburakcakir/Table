package com.mburakcakir.convention.plugin.app

import com.android.build.api.dsl.ApplicationExtension
import com.mburakcakir.convention.extensions.androidTestImplementation
import com.mburakcakir.convention.extensions.configureAndroidCompose
import com.mburakcakir.convention.extensions.configureBuildTypes
import com.mburakcakir.convention.extensions.configureKotlinAndroid
import com.mburakcakir.convention.extensions.implementation
import com.mburakcakir.convention.extensions.libs
import com.mburakcakir.convention.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AppMainPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugins.android.application.get().pluginId)

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                configureBuildTypes()
                defaultConfig {
                    targetSdk = libs.versions.targetSdk.get().toIntOrNull()
                    applicationId = "com.mburakcakir.table"
                    versionCode = 1
                    versionName = "1.0"
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }

            dependencies {
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.lifecycle.runtime.ktx)

                testImplementation(libs.junit)
                androidTestImplementation(libs.androidx.junit)
                androidTestImplementation(libs.androidx.espresso.core)
            }
        }
    }
}
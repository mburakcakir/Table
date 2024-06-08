package com.mburakcakir.convention.plugin.app

import com.android.build.api.dsl.ApplicationExtension
import com.mburakcakir.convention.extensions.configureAndroidCompose
import com.mburakcakir.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugins.android.application.get().pluginId)

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}
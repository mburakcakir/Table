package com.mburakcakir.convention.plugin.app

import com.android.build.api.dsl.ApplicationExtension
import com.mburakcakir.convention.extensions.configureHiltAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureHiltAndroid()
            }
        }
    }
}
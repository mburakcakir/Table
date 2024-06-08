package com.mburakcakir.convention.plugin.lib

import com.android.build.api.dsl.LibraryExtension
import com.mburakcakir.convention.extensions.configureHiltAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureHiltAndroid()
            }
        }
    }
}
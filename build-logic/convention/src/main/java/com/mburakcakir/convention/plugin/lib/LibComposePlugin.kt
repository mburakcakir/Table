package com.mburakcakir.convention.plugin.lib

import com.android.build.gradle.LibraryExtension
import com.mburakcakir.convention.extensions.configureAndroidCompose
import com.mburakcakir.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class LibComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugins.android.library.get().pluginId)

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}
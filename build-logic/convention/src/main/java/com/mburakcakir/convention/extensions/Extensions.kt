package com.mburakcakir.convention.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

internal fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) =
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)

internal fun ApplicationExtension.configureBuildTypes() {
    buildTypes.apply {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            signingConfig = signingConfigs.getByName("debug")
            resValue("string", "app_name", "Table - Dev ")
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
            resValue("string", "app_name", "Table")
        }
    }
}

internal fun DependencyHandlerScope.implementation(dependency: Any) = "implementation"(dependency)
internal fun DependencyHandlerScope.testImplementation(dependency: Any) =
    "testImplementation"(dependency)

internal fun DependencyHandlerScope.androidTestImplementation(dependency: Any) =
    "androidTestImplementation"(dependency)

internal fun DependencyHandlerScope.ksp(dependency: Any) = "ksp"(dependency)
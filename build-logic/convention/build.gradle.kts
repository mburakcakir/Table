import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.mburakcakir.convention.plugin"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.build.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    enableVersionCatalogAccess()
}


gradlePlugin {
    val appPath = "$group.app"
    val libPath = "$group.lib"

    plugins {
        register("appMain") {
            id = libs.plugins.table.app.main.get().pluginId
            implementationClass = "$appPath.AppMainPlugin"
        }
        register("appCompose") {
            id = libs.plugins.table.app.compose.get().pluginId
            implementationClass = "$appPath.AppComposePlugin"
        }
        register("appHilt") {
            id = libs.plugins.table.app.hilt.get().pluginId
            implementationClass = "$appPath.AppHiltPlugin"
        }

        register("libMain") {
            id = libs.plugins.table.lib.main.get().pluginId
            implementationClass = "$libPath.LibMainPlugin"
        }
        register("libCompose") {
            id = libs.plugins.table.lib.compose.get().pluginId
            implementationClass = "$libPath.LibComposePlugin"
        }
        register("libHilt") {
            id = libs.plugins.table.lib.hilt.get().pluginId
            implementationClass = "$libPath.LibHiltPlugin"
        }
    }
}

fun DependencyHandlerScope.enableVersionCatalogAccess() {
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
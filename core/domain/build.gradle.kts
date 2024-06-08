plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.hilt)
}

android {
    namespace = "com.mburakcakir.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.common)
    implementation(project(":core:network"))
}


plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.compose)
}

android {
    namespace = "com.mburakcakir.core.ui"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(libs.coil.compose)
}
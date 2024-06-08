plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.hilt)
    alias(libs.plugins.table.lib.compose)
}

android {
    namespace = "com.mburakcakir.feature.teamdetail"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.data)
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
}
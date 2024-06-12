plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.hilt)
}

android {
    namespace = "com.mburakcakir.core.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.retrofit)
}
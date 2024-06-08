plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.hilt)
}

android {
    namespace = "com.mburakcakir.core.network"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
}
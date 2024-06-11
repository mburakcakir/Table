plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.compose)
}

android {
    namespace = "com.mburakcakir.core.model"
}

dependencies {
    implementation(libs.converter.gson)
}

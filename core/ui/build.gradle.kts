plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.compose)
}

android {
    namespace = "com.mburakcakir.core.ui"
}

dependencies {
    implementation(libs.coil.compose)
}
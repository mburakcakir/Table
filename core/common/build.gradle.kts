import com.mburakcakir.convention.extensions.libs

plugins {
    alias(libs.plugins.table.lib.main)
    alias(libs.plugins.table.lib.compose)
}

android {
    namespace = "com.mburakcakir.core.common"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.converter.gson)
}
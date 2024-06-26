plugins {
    alias(libs.plugins.table.app.main)
    alias(libs.plugins.table.app.hilt)
    alias(libs.plugins.table.app.compose)
}

android {
    namespace = "com.mburakcakir.table"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.feature.leagues)
    implementation(projects.feature.standings)
    implementation(projects.feature.teamdetail)
}
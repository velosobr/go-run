plugins {
    alias(libs.plugins.gorun.android.library)
}

android {
    namespace = "com.velosobr.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}
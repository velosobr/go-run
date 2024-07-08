plugins {
    alias(libs.plugins.gorun.android.library)
    alias(libs.plugins.gorun.jvm.ktor)

}

android {
    namespace = "com.velosobr.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}
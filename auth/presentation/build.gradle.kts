plugins {
    alias(libs.plugins.gorun.android.feature.ui)
}

android {
    namespace = "com.velosobr.auth.presentation"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.auth.domain)

}
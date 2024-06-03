plugins {
    alias(libs.plugins.gorun.android.library)
}

android {
    namespace = "com.velosobr.run.network"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.data)
}
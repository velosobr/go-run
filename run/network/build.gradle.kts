plugins {
    alias(libs.plugins.gorun.android.library)
    alias(libs.plugins.gorun.jvm.ktor)

}

android {
    namespace = "com.velosobr.run.network"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.data)
}
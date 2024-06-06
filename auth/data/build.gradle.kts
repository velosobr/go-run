plugins {
    alias(libs.plugins.gorun.android.library)
    alias(libs.plugins.gorun.jvm.ktor)
}

android {
    namespace = "com.velosobr.auth.data"
}

dependencies {

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)

}
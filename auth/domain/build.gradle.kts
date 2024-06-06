plugins {
    alias(libs.plugins.gorun.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}

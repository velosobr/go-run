plugins {
    alias(libs.plugins.gorun.android.library)
}

android {
    namespace = "com.velosobr.core.database"
}

dependencies {
   implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)
}
plugins {
    alias(libs.plugins.gorun.android.library)
    alias(libs.plugins.gorun.android.room)
}

android {
    namespace = "com.velosobr.core.database"
}

dependencies {
   implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)
}
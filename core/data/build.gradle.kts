plugins {
   alias(libs.plugins.gorun.android.library)
}

android {
    namespace = "com.velosobr.core.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}
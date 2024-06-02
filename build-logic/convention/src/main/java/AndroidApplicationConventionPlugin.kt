import com.android.build.api.dsl.ApplicationExtension
import com.velosobr.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.configuration.internal.UserCodeApplicationContext.Application
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()

                    versionName = libs.findVersion("projectversionName").get().toString()
                }
            }
        }
    }
}
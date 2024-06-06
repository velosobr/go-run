import com.android.build.api.dsl.LibraryExtension
import com.velosobr.convention.addUiLayerDependencies
import com.velosobr.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureUiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("gorun.android.library.compose")
            }
            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}
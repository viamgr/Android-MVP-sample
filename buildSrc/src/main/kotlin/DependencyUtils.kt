import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.exclude

fun Dependency.getAsString() =
    "$dependency:$dependencyVersion"


fun DependencyHandler.addDependencies(dependencies: List<Dependency>) {
    dependencies.forEach { dependency ->
        addDependency(dependency)
    }
}

fun DependencyHandler.addDependency(dependency: Dependency) {
    when (dependency.dependencyType) {
        DependencyType.IMPLEMENTATION -> addDependencyInner("implementation", dependency)
        DependencyType.KAPT -> addDependencyInner("kapt", dependency)
        DependencyType.ANNOTATION_PROCESSOR -> addDependencyInner("annotationProcessor", dependency)
        DependencyType.TEST_IMPL -> addDependencyInner("testImplementation", dependency)
        DependencyType.KAPT_TEST -> addDependencyInner("kaptTest", dependency)
        DependencyType.ANDROID_TEST_IMPL -> addDependencyInner(
            "androidTestImplementation",
            dependency
        )
    }
}

fun DependencyHandler.addPlatformDependencies(dependencies: List<String>) {
    dependencies.forEach { dependency ->
        add("implementation", dependency)
    }
}


private fun DependencyHandler.addDependencyInner(
    configurationName: String,
    dependency: Dependency,
) {
    if (dependency.excludes.isEmpty()) {
        add(configurationName, dependency.getAsString())
    } else {
        @Suppress("RedundantSamConstructor")
        addDependencyTo(
            dependencies = this,
            configuration = configurationName,
            dependencyNotation = dependency.getAsString(),
            configurationAction = Action<ExternalModuleDependency> {
                dependency.excludes.forEach {
                    exclude(it.group, it.module)
                }
            }
        )
    }
}

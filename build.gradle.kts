buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.GradleClassPath.androidGradlePlugin)
        classpath(BuildPlugins.GradleClassPath.kotlinGradlePlugin)
        classpath(BuildPlugins.GradleClassPath.hiltGradlePlugin)
        classpath(BuildPlugins.GradleClassPath.kotlinSerialization)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
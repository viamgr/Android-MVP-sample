import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.daggerHiltPlugin)
    id(BuildPlugins.Apply.kotlinxSerialization)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
    }

    buildTypes {
        val gradleLocalProperties = gradleLocalProperties(rootDir)

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            buildConfigField(
                "String",
                "MarvelPrivateKey",
                gradleLocalProperties.getProperty("MarvelPrivateKey")
            )
            buildConfigField(
                "String",
                "MarvelPublicKey",
                gradleLocalProperties.getProperty("MarvelPublicKey")
            )
        }
        getByName("debug") {
            isMinifyEnabled = true

            buildConfigField(
                "String",
                "MarvelPrivateKey",
                gradleLocalProperties.getProperty("MarvelPrivateKey")
            )
            buildConfigField(
                "String",
                "MarvelPublicKey",
                gradleLocalProperties.getProperty("MarvelPublicKey")
            )
        }
    }
    compileOptions {
        sourceCompatibility = ConfigData.jvmTarget
        targetCompatibility = ConfigData.jvmTarget
    }

    // For Kotlin projects
    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget.toString()
        freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }

    kapt { generateStubs = true }
}

dependencies {

    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.DATA))

    addDependencies(RemoteModuleDependencies.getDependencies())
}

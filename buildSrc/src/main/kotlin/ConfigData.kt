import org.gradle.api.JavaVersion

object ConfigData {
    const val compileSdkVersion = 32
    const val minSdkVersion = 21
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
    val jvmTarget = JavaVersion.VERSION_11
    const val appID = "com.example.mvpexample"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}


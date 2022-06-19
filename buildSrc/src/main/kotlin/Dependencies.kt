interface Dependencies {
    fun getDependencies(): List<Dependency>
}

internal object AllDependencies {
    val coreKtx = Dependency(
        dependency = "androidx.core:core-ktx",
        dependencyVersion = DependencyVersions.coreKtxVersion,
        dependencyType = DependencyType.IMPLEMENTATION,
    )

    val appcompat = Dependency(
        dependency = "androidx.appcompat:appcompat",
        dependencyVersion = DependencyVersions.appcompatVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val material = Dependency(
        dependency = "com.google.android.material:material",
        dependencyVersion = DependencyVersions.materialVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val constraintlayout = Dependency(
        dependency = "androidx.constraintlayout:constraintlayout",
        dependencyVersion = DependencyVersions.constraintlayoutVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val coroutinesAndroid = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-coroutines-android",
        dependencyVersion = DependencyVersions.coroutinesAndroidVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val coroutinesCore = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-coroutines-core",
        dependencyVersion = DependencyVersions.coroutinesCoreVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val hiltAndroid = Dependency(
        dependency = "com.google.dagger:hilt-android",
        dependencyVersion = DependencyVersions.hiltAndroidVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val coil = Dependency(
        dependency = "io.coil-kt:coil",
        dependencyVersion = DependencyVersions.coilVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val okhttp = Dependency(
        dependency = "com.squareup.okhttp3:okhttp",
        dependencyVersion = DependencyVersions.okhttpVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val okhttpLoggingInterceptor = Dependency(
        dependency = "com.squareup.okhttp3:logging-interceptor",
        dependencyVersion = DependencyVersions.okhttpVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val retrofit = Dependency(
        dependency = "com.squareup.retrofit2:retrofit",
        dependencyVersion = DependencyVersions.retrofitVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val retrofitSerializationConverter = Dependency(
        dependency = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter",
        dependencyVersion = DependencyVersions.retrofitSerializationConverterVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val kotlinxSerializationJson = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-serialization-json",
        dependencyVersion = DependencyVersions.serializationJsonVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val roomRuntime = Dependency(
        dependency = "androidx.room:room-runtime",
        dependencyVersion = DependencyVersions.roomVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val roomCommon = Dependency(
        dependency = "androidx.room:room-common",
        dependencyVersion = DependencyVersions.roomVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val roomKtx = Dependency(
        dependency = "androidx.room:room-ktx",
        dependencyVersion = DependencyVersions.roomVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val hiltCompiler = Dependency(
        dependency = "com.google.dagger:hilt-compiler",
        dependencyVersion = DependencyVersions.hiltAndroidVersion,
        dependencyType = DependencyType.KAPT
    )
    val roomCompiler = Dependency(
        dependency = "androidx.room:room-compiler",
        dependencyVersion = DependencyVersions.roomVersion,
        dependencyType = DependencyType.KAPT
    )
    val mockkAndroid = Dependency(
        dependency = "io.mockk:mockk-android",
        dependencyVersion = DependencyVersions.mockkVersion,
        dependencyType = DependencyType.TEST_IMPL
    )
    val mockk = Dependency(
        dependency = "io.mockk:mockk",
        dependencyVersion = DependencyVersions.mockkVersion,
        dependencyType = DependencyType.TEST_IMPL
    )
    val coroutineTest = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-coroutines-test",
        dependencyVersion = DependencyVersions.coroutinesAndroidVersion,
        dependencyType = DependencyType.TEST_IMPL
    )
    val coroutineTestImpl = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-coroutines-test",
        dependencyVersion = DependencyVersions.coroutinesAndroidVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val truth = Dependency(
        dependency = "com.google.truth:truth",
        dependencyVersion = DependencyVersions.truthVersion,
        dependencyType = DependencyType.TEST_IMPL
    )

    val junitTest = Dependency(
        dependency = "junit:junit",
        dependencyVersion = DependencyVersions.junitVersion,
        dependencyType = DependencyType.TEST_IMPL
    )

    val junitImpl = Dependency(
        dependency = "junit:junit",
        dependencyVersion = DependencyVersions.junitVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val junitKtx = Dependency(
        dependency = "androidx.test.ext:junit-ktx",
        dependencyVersion = DependencyVersions.junitKtxVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val testRunner = Dependency(
        dependency = "androidx.test:runner",
        dependencyVersion = DependencyVersions.testRunnerVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val espressoCore = Dependency(
        dependency = "androidx.test.espresso:espresso-core",
        dependencyVersion = DependencyVersions.espressoCoreVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val espressoContrib = Dependency(
        dependency = "androidx.test.espresso:espresso-contrib",
        dependencyVersion = DependencyVersions.espressoContribVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val hiltAndroidTesting = Dependency(
        dependency = "com.google.dagger:hilt-android-testing",
        dependencyVersion = DependencyVersions.hiltAndroidTestingVersion,
        dependencyType = DependencyType.ANDROID_TEST_IMPL
    )

    val hiltAndroidCompiler = Dependency(
        dependency = "com.google.dagger:hilt-android-compiler",
        dependencyVersion = DependencyVersions.hiltAndroidCompilerVersion,
        dependencyType = DependencyType.KAPT_TEST
    )


}



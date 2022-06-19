object AppModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.coreKtx,
        AllDependencies.appcompat,
        AllDependencies.material,
        AllDependencies.constraintlayout,
        AllDependencies.coroutinesAndroid,
        AllDependencies.coroutinesCore,
        AllDependencies.coil,
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.mockk,
        AllDependencies.coroutineTestImpl,
        AllDependencies.coroutineTest,
        AllDependencies.truth,
        AllDependencies.junitTest,
        AllDependencies.junitKtx,
        AllDependencies.testRunner,
        AllDependencies.espressoCore,
        AllDependencies.espressoContrib,
        AllDependencies.hiltAndroidTesting,
        AllDependencies.hiltAndroidCompiler,
    )
}

object CoreModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.hiltAndroid,
        AllDependencies.coroutinesAndroid,
        AllDependencies.coroutinesCore,
        AllDependencies.mockk,
        AllDependencies.coroutineTestImpl,
        AllDependencies.coroutineTest,
        AllDependencies.truth,
        AllDependencies.junitTest,
    )

}

object CacheModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.roomRuntime,
        AllDependencies.roomCommon,
        AllDependencies.roomKtx,
        AllDependencies.roomCompiler,
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.mockk,
        AllDependencies.coroutineTestImpl,
        AllDependencies.coroutineTest,
        AllDependencies.truth,
        AllDependencies.junitTest,
        AllDependencies.junitImpl,
    )

}

object DataModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.mockk,
        AllDependencies.coroutineTestImpl,
        AllDependencies.coroutineTest,
        AllDependencies.truth,
        AllDependencies.junitTest,
        AllDependencies.junitImpl,
    )

}

object DomainModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.coroutinesAndroid,
        AllDependencies.coroutinesCore,
        AllDependencies.mockk,
        AllDependencies.coroutineTestImpl,
        AllDependencies.coroutineTest,
        AllDependencies.truth,
        AllDependencies.junitTest,
        AllDependencies.junitImpl,
    )

}

object UiCommonModuleDependencies : Dependencies {
    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.material,
    )
}

object AndroidTestSharedModuleDependencies : Dependencies {
    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.coroutinesAndroid,
        AllDependencies.coroutinesCore,
        AllDependencies.mockkAndroid,
        AllDependencies.mockk,
        AllDependencies.coroutineTestImpl,
        AllDependencies.coroutineTest,
        AllDependencies.truth,
        AllDependencies.junitTest,
        AllDependencies.junitImpl,
    )
}

object RemoteModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.okhttp,
        AllDependencies.okhttpLoggingInterceptor,
        AllDependencies.retrofit,
        AllDependencies.retrofitSerializationConverter,
        AllDependencies.kotlinxSerializationJson,
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
    )

}

val kotlin_version = "1.5.10"
val retrofit_version = "2.9.0"
val hilt_version = "2.35"
val coroutines_version = "1.3.9"
val arch_version = "2.1.0"


object KotlinDependencies {

    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"

    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

    val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version"

}


object AndroidXDependencies {

    val core_ktx = "androidx.core:core-ktx:1.6.0"
    val appcompat = "androidx.appcompat:appcompat:1.3.0"
    val material = "com.google.android.material:material:1.3.0"


}

object TestDependencies {

    val jUnit = "junit:junit:4.+"

    val androidx_core_testing = "androidx.arch.core:core-testing:$arch_version"

    val androidx_truth = "androidx.test.ext:truth:1.3.0"
    val google_truth = "com.google.truth:truth:1.1"


}

object AndroidTestDependencies {

    val androidx_test_ext_jUnit = "androidx.test.ext:junit:1.1.3"
    val espresso_core = "androidx.test.espresso:espresso-core:3.4.0"


}


object DataDependencies {

    val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"

    val moshi = "com.squareup.moshi:moshi-kotlin:1.11.0"
    val moshi_converter = "com.squareup.retrofit2:converter-moshi:$retrofit_version"


}


object JetpackDependencies {
    val hilt_android = "com.google.dagger:hilt-android:$hilt_version"
    val hilt_compiler = "com.google.dagger:hilt-compiler:$hilt_version"

}
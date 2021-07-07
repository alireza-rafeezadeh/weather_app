private const val kotlin_version = "1.5.10"
private const val retrofit_version = "2.9.0"
private const val hilt_version = "2.35"
private const val coroutines_version = "1.3.9"
private const val arch_version = "2.1.0"
private const val lifecycle_version = "2.2.0"
private const val nav_version = "2.3.2"
private const val glide_version = "4.12.0"


object KotlinDependencies {

    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
}

object AndroidXDependencies {

    val core_ktx = "androidx.core:core-ktx:1.6.0"
    val appcompat = "androidx.appcompat:appcompat:1.3.0"
    val material = "com.google.android.material:material:1.3.0"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    val location_services = "com.google.android.gms:play-services-location:18.0.0"
}

object TestDependencies {

    val jUnit = "junit:junit:4.+"
    val androidx_core_testing = "androidx.arch.core:core-testing:$arch_version"
    val androidx_truth = "androidx.test.ext:truth:1.3.0"
    val google_truth = "com.google.truth:truth:1.1"
    val android_test_runner = "com.android.support.test:runner:1.0.2"
    val navigation_testing = "androidx.navigation:navigation-testing:$nav_version"
    val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version"
    val androidx_test_ext_jUnit = "androidx.test.ext:junit:1.1.3"
    val espresso_core = "androidx.test.espresso:espresso-core:3.4.0"
    val hilt_tesiting = "com.google.dagger:hilt-android-testing:2.35"
}

object DataDependencies {

    val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    val moshi = "com.squareup.moshi:moshi-kotlin:1.11.0"
    val moshi_converter = "com.squareup.retrofit2:converter-moshi:$retrofit_version"
}

object JetpackDependencies {

    val hilt_android = "com.google.dagger:hilt-android:$hilt_version"
    val hilt_compiler = "com.google.dagger:hilt-compiler:$hilt_version"
    val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    val viewmodel_saved_state =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"
    val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    val lifecycle_compiler_java_8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
    val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:2.35"
    val navigaiton_fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
    val navigaiton_ui = "androidx.navigation:navigation-ui-ktx:$nav_version"
}

object GlideDependencies {

    val glide = "com.github.bumptech.glide:glide:$glide_version"
    val glide_compiler = "com.github.bumptech.glide:compiler:$glide_version"
}
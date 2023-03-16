object Versions {
    internal const val kotlinVersion = "1.7.10"
    internal const val compose_version = "1.3.0-beta01"
    internal const val compose_activity_version = "1.5.1"
    internal const val core_version = "1.9.0"
    internal const val appcompat_version = "1.6.1"
    internal const val compose_material3_version = "1.0.0-beta01"
    internal const val lifecycle_version = "2.5.1"
    internal const val junit_version = "4.13.2"
    internal const val junit_ext_version = "1.1.3"
    internal const val espresso_version = "3.4.0"
    internal const val navigation_compose_version = "2.5.0-rc01"
    internal const val compose_runtime_version = "1.2.0-beta02"

    internal const val compose_hilt_version = "1.0.0"
    internal const val dagger_version = "2.43.2"
    internal const val room_version = "2.4.2"
    internal const val room_paging_version = "2.4.2"
    internal const val paging_version = "3.1.1"
    internal const val paging_compose_version = "1.0.0-alpha16"

    internal const val retrofit_version = "2.9.0"
    internal const val okHttp_version = "4.9.3"
    internal const val moshi_version = "1.13.0"
    internal const val moshi_converter_version = "2.9.0"
    internal const val gson_version = "2.8.9"
    internal const val gsonConverter_version = "2.9.0"

    internal const val coil_version = "2.1.0"

    internal const val firebaseBom_version = "30.4.0"

    internal const val timber_version = "5.0.1"

    internal const val shimmer_version = "0.5.0"

    internal const val picasso_version = "2.71828"
}
object Dependencies {
    const val androidxCore = "androidx.core:core-ktx:${Versions.core_version}" //implementation
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}" //implementation
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose_version}" //implementation
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.compose_material3_version}" //implementation
    const val composeUiTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}" //implementation
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}" //implementation
    const val composeActivity = "androidx.activity:activity-compose:${Versions.compose_activity_version}" //implementation

    const val junit = "junit:junit:${Versions.junit_version}" //testImplementation
    const val junitExt = "androidx.test.ext:junit:${Versions.junit_ext_version}" //androidTestImplementation
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}" //androidTestImplementation
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}" //androidTestImplementation
    const val composeUiToolingTest = "androidx.compose.ui:ui-tooling:${Versions.compose_version}" //implementation
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose_version}" //debugImplementation

    //navigation
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigation_compose_version}" //api
    const val navigationRuntime = "androidx.compose.runtime:runtime:${Versions.compose_runtime_version}" //implementation
    const val navigatioCcomposeHilt = "androidx.hilt:hilt-navigation-compose:${Versions.compose_hilt_version}" //implementation

    //dagger
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.dagger_version}" //implementation
    const val daggerHiltKapt = "com.google.dagger:hilt-compiler:${Versions.dagger_version}" // kapt
    const val daggerHilTesting = "com.google.dagger:hilt-android-testing:${Versions.dagger_version}" //androidTestImplementation
    const val daggerHiltKaptTesting = "com.google.dagger:hilt-compiler:${Versions.dagger_version}" //kaptAndroidTest
    const val daggerHiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.dagger_version}" // testImplementation
    const val daggerHiltKaptAndroidTesting = "com.google.dagger:hilt-compiler:${Versions.dagger_version}" //kaptTest

    //Room
     const val room = "androidx.room:room-runtime:${Versions.room_version}" //implementation
     const val roomKsp = "androidx.room:room-compiler:${Versions.room_version}" //ksp
     const val ktxRoomCoroutines = "androidx.room:room-ktx:${Versions.room_version}" //implementation
     const val roomTesting = "androidx.room:room-testing:${Versions.room_version}" //testImplementation
     const val roomPaging = "androidx.room:room-paging:${Versions.room_paging_version}" //implementation

    //Paging
    const val paging = "androidx.paging:paging-runtime:${Versions.paging_version}" //implementation
    const val pagingTest = "androidx.paging:paging-common:${Versions.paging_version}" //testImplementation
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.paging_compose_version}" //implementation

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}" //implementation

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp_version}" //implementation
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp_version}" //implementation

    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}" //implementation
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshi_converter_version}" //implementation

    const val gson = "com.google.code.gson:gson:${Versions.gson_version}" //implementation
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter_version}" //implementation

    //Coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil_version}" //implementation

    //Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom_version}" //implementation
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx" //implementation

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}" //implementation

    //Shimmer
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer_version}"

    //Picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso_version}"
}

object ConfigVersions{
    const val compileSdkVersion = 33
    const val buildToolsVersion = "33.0.0"
    const val minSdkVersion = 21
    const val targetSdkVersion = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val composeVersion = Versions.compose_version
    const val kotlinVersion = Versions.kotlinVersion
}
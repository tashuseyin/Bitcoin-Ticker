
object Libraries {

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

    //room
    const val room = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.roomVersion}"

    //dataStore
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"


    //coroutines
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    //coroutines play service
    const val coroutinesPlayService = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesPlayServiceVersion}"


    //androidX
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompat}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycleRuntime}"
    const val androidxFragment = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"

    //dagger - hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    //splashApi
    const val splash = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"

    //constraintLayout
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVerison}"

    //material
    const val material = "com.google.android.material:material:${Versions.materialVersion}"

    //firebase
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuthVersion}"
    const val firebaseFireStore = "com.google.firebase:firebase-firestore-ktx:${Versions.firebasefireStoreVersion}"

    //lifecycle components
    const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelVersion}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedateVersion}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeVersion}"


    //navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentVersion}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUIVersion}"

    //coil
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
}
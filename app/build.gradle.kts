plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    id(Plugins.hilt)
    //id(Plugins.googleServices)
    id(Plugins.parcelizePlugin)
    kotlin(Plugins.kapt)
}

android {
    namespace = Config.applicationId
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = "com.example.bitcoin_ticker"
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Config.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    //androidx
    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxAppcompat)
    implementation(Libraries.androidxLifecycleRuntime)

    //retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.okhttp)
    implementation(Libraries.loggingInterceptor)

    //splash
    implementation(Libraries.splash)

    //hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    //constraintLayout
    implementation(Libraries.constraintLayout)

    //material
    implementation(Libraries.material)

    //firebase
    implementation(Libraries.firebaseAuth)
    implementation(Libraries.firebaseFireStore)

    //navigation
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUI)

    //coil
    implementation(Libraries.coil)

    //lifecycle component
    implementation(Libraries.lifecycleViewmodel)
    implementation(Libraries.lifecycleLivedata)
    implementation(Libraries.lifecycleRuntime)
}
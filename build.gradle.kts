buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
    }
}

plugins { 
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.google.gms.google-services") version "4.3.14" apply false
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
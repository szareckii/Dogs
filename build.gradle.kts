buildscript {
    /*
    ext {
        composeVersion = '1.6.0'
        accompanistVersion = '0.34.0'
        roomVersion = '2.6.1'
        cameraxVersion = '1.3.1'
        paging_version = "3.2.1"
    }
     */
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath ("com.android.tools.build:gradle:8.2.2")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}
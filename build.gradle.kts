//// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
//    dependencies {
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
//        classpath("com.android.tools.build:gradle:7.3.1")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
//        // classpath("org.jetbrains.kotlin:kotlin-android-extensions-runtime:1.6.10")
//    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
}

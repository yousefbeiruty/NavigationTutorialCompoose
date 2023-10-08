import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("dagger.hilt.android.plugin")

   // id("dagger.hilt.android.plugin") version "2.48"
}

android {
    namespace = "com.example.navigationtutorial"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.navigationtutorial"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        compileSdkPreview = "UpsideDownCake"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion ="1.4.0" //"1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


    val  compose_version = "1.5.1"
    val  hilt_version = "2.48"
    val  hilt_navigation_compose_version = "1.0.0"
    val  lifecycle_version = "2.7.0-alpha02"
    val  retrofit_version = "2.9.0"
    val  okhttp_version = "5.0.0-alpha.1"
    val  gson_version = "2.10"


dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.ui:ui-graphics:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.2")
    implementation("androidx.appcompat:appcompat:1.6.1")

    // implementation("androidx.navigation:navigation-safe-args-generator:2.7.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  //  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material:material:$compose_version")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.compose.foundation:foundation:$compose_version")

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-work:$hilt_navigation_compose_version")
    kapt("androidx.hilt:hilt-compiler:$hilt_navigation_compose_version")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose_version")
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // OkHttp Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")

    // Gson
    implementation("com.google.code.gson:gson:$gson_version")

    // Paging
    implementation("androidx.paging:paging-common-ktx:3.2.1")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.2.1")

    implementation("de.westnordost:osmapi-notes:1.3") {
        exclude(group = "xmlpull", module = "xmlpull")
       // exclude(group = "xpp3", module = "xpp3")
    }
}
//subprojects {
//    configurations.all {
//        resolutionStrategy {
//            force("xmlpull:xmlpull:1.1.3.1")
//            force("xpp3:xpp3:1.1.4c")
//        }
//    }
//}
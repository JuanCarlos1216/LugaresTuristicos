import org.jetbrains.kotlin.utils.addToStdlib.safeAs

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.juanca.exploreit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.juanca.exploreit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL", "\"https://7zpvppaxonsljd4n3rfzhlmoh40bvryi.lambda-url.us-east-1.on.aws\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "API_URL", "\"https://7zpvppaxonsljd4n3rfzhlmoh40bvryi.lambda-url.us-east-1.on.aws/dev\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        buildConfig = true
        viewBinding = true
    }

    flavorDimensions.add("mode")
    productFlavors {
        create("free") {
            dimension = "mode"
            buildConfigField("String", "API_URL", "\"https://7zpvppaxonsljd4n3rfzhlmoh40bvryi.lambda-url.us-east-1.on.aws\"")
        }
        create("paid") {
            dimension = "mode"
            buildConfigField("String", "API_URL", "\"https://7zpvppaxonsljd4n3rfzhlmoh40bvryi.lambda-url.us-east-1.on.aws/dev\"")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //Room sqlite
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    //Navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.legacy.support.v4)
    //CardView
    implementation(libs.androidx.cardview)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //Images picasso
    implementation(libs.picasso)
    //Work viewmodel
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //Splash animation
    implementation(libs.androidx.core.splashscreen)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Google maps
    implementation(libs.play.services.maps)
}
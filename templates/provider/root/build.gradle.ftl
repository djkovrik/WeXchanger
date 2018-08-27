apply plugin: 'com.android.library'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-kapt'

android {
    compileSdkVersion 27
    defaultConfig {
        minSdkVersion 19
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    ${getConfigurationName("implementation")} fileTree(dir: 'libs', include: ['*.jar'])

    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    implementation project(':${coreModuleName}')

    implementation "com.google.dagger:dagger:2.17"
    kapt "com.google.dagger:dagger-compiler:2.17"
}
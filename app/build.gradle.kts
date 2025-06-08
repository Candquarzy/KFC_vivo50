import org.gradle.kotlin.dsl.implementation

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
}

android {
	namespace = "com.candy.v50"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.candy.v50"
		minSdk = 21
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation("com.squareup.retrofit2:retrofit:3.0.0")

	// Retrofit Scalar Converter (用于处理 String 响应)
	implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")
	// OkHttp (Retrofit 内部使用，建议显式添加，用于拦截器等)
	implementation ("com.squareup.okhttp3:okhttp:4.12.0")
	// Kotlinx Coroutines Android (协程)
	implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
	// JSoup (用于解析 HTML)
	implementation ("org.jsoup:jsoup:1.17.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}
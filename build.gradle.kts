import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

group = "ru.mkn.kotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.swing)
    implementation(libs.coroutines.debug)
    implementation(libs.coroutines.slf4j)

    implementation(libs.logback)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.mock)
    implementation(libs.retrofit.rxjava3)
    implementation(libs.retrofit.kotlinx.serialization)

    implementation(libs.okhttp)
    implementation(libs.rxjava3)
    implementation(libs.rxkotlin3)

    testImplementation(kotlin("test"))
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.coroutines.test)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    compilerOptions.freeCompilerArgs.add(
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
    )
}

sourceSets {
    this.getByName("main") {
        this.resources.srcDir("resources")
    }
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("src")
            resources.srcDir("resource")
        }
        test {
            kotlin.srcDir("test")
        }
    }

    jvmToolchain(JavaVersion.VERSION_21.majorVersion.toInt())
}

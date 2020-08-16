import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junitVersion = "5.5.1"
val junitPlatformVersion = "1.5.1"

plugins {
    kotlin("jvm") version "1.3.72"
}

group = "com.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "jacoco")

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

        // Mocks
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
        testImplementation("org.mockito:mockito-core:3.3.0")

        // Log
        implementation("org.slf4j:slf4j-api:1.7.30")
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "idea")
    group = "com.demo"
    version = "1.0.0-SNAPSHOT"
    repositories {
        jcenter()
        mavenLocal()
        mavenCentral()
    }

    tasks.test {
        useJUnitPlatform {
            includeEngines("junit-jupiter")
        }
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

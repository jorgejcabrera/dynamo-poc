group = "com.demo"
version = "1.0.0-SNAPSHOT"

plugins {
    kotlin("jvm")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))

    // DynamoDB
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.11.573")
    implementation("com.github.derjust:spring-data-dynamodb:5.1.0")

    // MySql
    implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")
    runtimeOnly("mysql:mysql-connector-java")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

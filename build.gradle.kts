
plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"

    application

    // Adds "shadowJar" task for generating JAR file
    id("com.github.johnrengelman.shadow") version "5.2.0"

    // Plugin for generating a docker image
    id("com.google.cloud.tools.jib") version "1.3.0"
}

group = "com.yngvark"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

println("Using java version: " + JavaVersion.current())

repositories {
    mavenLocal()
    jcenter()
    maven { setUrl("https://kotlin.bintray.com/ktor") }
}

dependencies {
    val kotlinVersion = "1.3.72"
    val ktorVersion = "1.3.2"

    // Ktor
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")

    // HTTP client library
    implementation("com.squareup.okhttp3:okhttp:3.8.0")

    // Deerialization
    implementation("com.google.code.gson:gson:2.8.6")

    // Serialization
    implementation("io.ktor:ktor-jackson:$ktorVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.2.3")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}


application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// Configuration for creating a docker image
jib {
    container {
        mainClass = "io.ktor.server.netty.EngineMain"
    }
    from {
        image = "openjdk:12-jdk"
    }
    to {
        image = "yngvark/bysykkel_sanntidsdata:0.0.1"
    }
}


plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.11"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
    id("org.flywaydb.flyway") version "8.5.1"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val ktorVersion = "2.3.11"
val exposedVersion: String by project
dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("ch.qos.logback:logback-classic:1.4.7")

    implementation("org.flywaydb:flyway-core:8.5.10")
    implementation("org.postgresql:postgresql:42.3.1")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    //implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")

    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
   // implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")
   // implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
}

flyway{
    url = "jdbc:postgresql://localhost:5432/university"
    user = "postgres"
    password = "changeme"
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
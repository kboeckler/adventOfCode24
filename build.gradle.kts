// For `KotlinCompile` task below
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.20" // Kotlin version to use
    application // Application plugin. Also see 1️⃣ below the code
}

group = "com.github.kboeckler.aoc24" // A company name, for example, `org.jetbrains`
version = "1.0-SNAPSHOT" // Version to assign to the built artifact

repositories { // Sources of dependencies. See 2️⃣
    mavenCentral() // Maven Central Repository. See 3️⃣
}

dependencies { // All the libraries you want to use. See 4️⃣
    implementation("org.reflections:reflections:0.10.2")

    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.5.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.23.1")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j2-impl
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.23.1")

    // Copy dependencies' names after you find them in a repository
    testImplementation(kotlin("test")) // The Kotlin test library
}

tasks.test { // See 5️⃣
    useJUnitPlatform() // JUnitPlatform for tests. See 6️⃣
}

kotlin { // Extension for easy setup
    jvmToolchain(17) // Target version of generated JVM bytecode. See 7️⃣
}

application {
    mainClass.set("de.kevinboeckler.aoc23.Aoc23Kt") // The main class of the application
}

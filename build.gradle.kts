plugins {
    kotlin("jvm") version "2.0.20" // Kotlin version to use
    application
}

group = "com.github.kboeckler.aoc24" // A company name, for example, `org.jetbrains`
version = "1.0-SNAPSHOT" // Version to assign to the built artifact

repositories { // Sources of dependencies.
    mavenCentral() // Maven Central Repository.
}

dependencies { // All the libraries you want to use.⃣
    implementation("org.reflections:reflections:0.10.2")

    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.5.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.23.1")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j2-impl
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.23.1")

    // Copy dependencies' names after you find them in a repository
    testImplementation(kotlin("test")) // The Kotlin test library
}

tasks.test {
    useJUnitPlatform() // JUnitPlatform for tests.
}

kotlin { // Extension for easy setup
    jvmToolchain(17) // Target version of generated JVM bytecode.
}

application {
    mainClass.set("com.github.kboeckler.aoc24.Aoc24Kt") // The main class of the application
}

package com.github.kboeckler.aoc24

import org.reflections.Reflections
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.measureTimeMillis

var session: String? = null

const val AOC_YEAR = 2024

val logger: Logger = LoggerFactory.getLogger("com.github.kboeckler.aoc24")

fun main() {
    session = readSessionFromFile()
    Reflections("com.github.kboeckler.aoc24").getSubTypesOf(Day::class.java)
        .sortedWith(::compareSolutionNameTo)
        .mapNotNull(::instantiateSolution)
        .forEach(::runSolution)
}

fun compareSolutionNameTo(one: Class<out Day>, another: Class<out Day>): Int {
    val matchOne = "(.*Day)(\\d+).*".toRegex().matchEntire(one.simpleName)
    val matchAnother = "(.*Day)(\\d+).*".toRegex().matchEntire(another.simpleName)
    if (matchOne == null || matchAnother == null) {
        return one.simpleName.compareTo(another.simpleName)
    }
    val pureOne = matchOne.groupValues[1]
    val pureAnother = matchAnother.groupValues[1]
    val compPure = pureOne.compareTo(pureAnother)
    if (compPure != 0) {
        return compPure
    }
    val numberOne = matchOne.groupValues[2].toInt()
    val numberTwo = matchAnother.groupValues[2].toInt()
    val compNumber = numberOne.compareTo(numberTwo)
    if (compNumber != 0) {
        return compNumber
    }
    return one.simpleName.compareTo(another.simpleName)
}

private fun instantiateSolution(solutionClass: Class<out Day>): Day? {
    try {
        return solutionClass.getDeclaredConstructor().newInstance()
    } catch (_: Throwable) {
        logger.warn("Could not create solution %s, since there was no empty constructor".format(solutionClass.simpleName))
        return null
    }
}

private fun runSolution(solution: Day) {
    val errors = mutableListOf<Throwable>()
    var result1: Any = "_"
    var result2: Any = "_"
    val timeSpentInMs = measureTimeMillis {
        try {
            val input = readInput(solution)
            result1 = try {
                solution.part1(input)
            } catch (err: Throwable) {
                errors.add(err)
                null
            } ?: "_"
            result2 = try {
                solution.part2(input)
            } catch (err: Throwable) {
                errors.add(err)
                null
            } ?: "_"
        } catch (err: Throwable) {
            errors.add(err)
        }
    }
    println("%s: %s, %s (%dms)".format(solution.name(), result1, result2, timeSpentInMs))
    errors.forEach { err -> logger.error("Error solving %s".format(solution.name()), err) }
}

interface Day {
    fun part1(input: String): Any?
    fun part2(input: String): Any?
}

fun Day.name(): String {
    return this.javaClass.simpleName
}

fun Day.day(): Int {
    return this.javaClass.simpleName.replace("[a-zA-Z]*".toRegex(), "").toInt()
}

fun Day.inputFilename(): String {
    return "%s.txt".format(day())
}

fun readInput(solution: Day): String {
    val filename = solution.inputFilename()
    val inputFile = File("src/main/resources/$filename")
    if (!inputFile.exists()) {
        try {
            logger.info("No file $inputFile exists, trying to download.")
            val input = download(AOC_YEAR, solution.day())
            try {
                inputFile.writeText(input)
                logger.info("Downloaded input and wrote it to $inputFile.")
            } catch (err: Throwable) {
                logger.warn("Downloaded input, but could not write it to file $inputFile: $err")
            }
            return input.replace("\r\n", "\n")
        } catch (err: Throwable) {
            logger.warn("Error downloading input for ${solution.name()}: $err")
        }
    }
    return inputFile.readText().replace("\r\n", "\n")
}

fun readSessionFromFile(): String? {
    val sessionFile = File(".aoc_session")
    if (!sessionFile.canRead()) {
        logger
            .warn("In order to download missing input automatically you need to provide a .aoc_session file containing the session value.")
        return null
    }
    return sessionFile.readText().trimEnd('\r', '\n')
}

private fun download(year: Int, day: Int): String {
    if (session == null) {
        throw java.lang.IllegalStateException("Missing session value from .aoc_session file. No download is possible.")
    }
    val client = HttpClient.newBuilder().build();
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://adventofcode.com/$year/day/$day/input"))
        .header("Cookie", "session=$session")
        .build();
    val response = client.send(request, HttpResponse.BodyHandlers.ofString());
    var text = response.body()
    if (text.endsWith("\n")) {
        text = text.dropLast(1)
    }
    if ("Puzzle inputs differ by user.  Please log in to get your puzzle input." == text) {
        throw java.lang.IllegalStateException("Invalid session value inside .aoc_session file. Response from server: $text")
    }
    if ("404 Not Found" == text) {
        throw java.lang.IllegalStateException("Invalid downloading resource. Response from server: $text")
    }
    return text
}

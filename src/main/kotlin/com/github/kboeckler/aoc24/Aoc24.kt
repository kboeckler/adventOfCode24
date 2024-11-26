package com.github.kboeckler.aoc24

import org.apache.logging.log4j.kotlin.Logging
import org.reflections.Reflections
import org.slf4j.LoggerFactory
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.measureTimeMillis

var session: String? = null

const val AOC_YEAR = 2024

fun main() {
    session = readSessionFromFile()
    Reflections("com.github.kboeckler.aoc24").getSubTypesOf(Day::class.java).filterNot { it.equals(EmptyDay::class.java) }
        .sortedWith(::compareSolutionNameTo).forEach(::runSolution)
}

fun compareSolutionNameTo(one: Class<out Day>?, another: Class<out Day>?): Int {
    val matchOne = "(.*Day)(\\d+)\$".toRegex().matchEntire(one!!.simpleName)
    val matchAnother = "(.*Day)(\\d+)\$".toRegex().matchEntire(another!!.simpleName)
    val pureOne = if (matchOne != null) matchOne.groupValues[1] else one.simpleName
    val pureAnother = if (matchAnother != null) matchAnother.groupValues[1] else one.simpleName
    val compPure = pureOne.compareTo(pureAnother)
    if (compPure != 0) {
        return compPure
    }
    val numberOne = if (matchOne != null) matchOne.groupValues[2].toInt() else 0
    val numberTwo = if (matchAnother != null) matchAnother.groupValues[2].toInt() else 0
    return numberOne.compareTo(numberTwo)
}

private fun runSolution(solutionClass: Class<out Day>) {
    val errors = mutableListOf<Throwable>()
    val solution: Day = try {
        solutionClass.getDeclaredConstructor().newInstance()!!
    } catch (err: Throwable) {
        errors.add(err)
        EmptyDay()
    }
    var result1: Any = "_"
    var result2: Any = "_"
    val timeSpentInMs = measureTimeMillis {
        try {
            val input = solution.readInput()
            result1 = try {
                solution.part1(input)
            } catch (err: Throwable) {
                errors.add(err)
                "_"
            }
            result2 = try {
                solution.part2(input)
            } catch (err: Throwable) {
                errors.add(err)
                "_"
            }
        } catch (err: Throwable) {
            errors.add(err)
        }
    }
    println("%s: %s, %s (%dms)".format(solution.name(), result1, result2, timeSpentInMs))
    errors.forEach { err -> err.printStackTrace() }
}

abstract class Day(name: String?, day: Int?) : Logging {

    private val name: String?

    private val day: Int?

    init {
        this.name = name
        this.day = day
    }

    constructor() : this(null, null) {
    }

    fun name(): String {
        return this.name ?: this.javaClass.simpleName
    }

    private fun day(): Int {
        return this.day ?: this.javaClass.simpleName.replace("[a-zA-Z]*".toRegex(), "").toInt()
    }

    abstract fun part1(input: String): Any
    abstract fun part2(input: String): Any
    open fun readInput(): String {
        val filename = "%s.txt".format(name())
        val inputFile = File("src/main/resources/$filename")
        if (!inputFile.exists()) {
            try {
                val input = download(AOC_YEAR, day())
                try {
                    inputFile.writeText(input)
                } catch (err: Throwable) {
                    logger.warn("Downloaded input, but could not write it to file $inputFile: $err")
                }
                return input.replace("\r\n", "\n")
            } catch (err: Throwable) {
                logger.warn("Error downloading input for ${name()}: $err")
            }
        }
        return inputFile.readText().replace("\r\n", "\n")
    }
}

class EmptyDay : Day() {
    override fun part1(input: String): Any {
        return "_"
    }

    override fun part2(input: String): Any {
        return "_"
    }

    override fun readInput(): String {
        return ""
    }
}

fun readSessionFromFile(): String? {
    val sessionFile = File(".aoc_session")
    if (!sessionFile.canRead()) {
        LoggerFactory.getLogger("Aoc23")
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

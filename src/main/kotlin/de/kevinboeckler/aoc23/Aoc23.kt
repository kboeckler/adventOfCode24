package de.kevinboeckler.aoc23

import java.io.File

fun main() {
    solutions.forEach {
        val input = File("src/main/resources/%s.txt".format(it.name())).readText()
        val errors = mutableListOf<Error>()
        val result1: Any = try {
            it.part1(input)
        } catch (err: Error) {
            errors.add(err)
            "_"
        }
        val result2: Any = try {
            it.part2(input)
        } catch (err: Error) {
            errors.add(err)
            "_"
        }
        println("%s: %s, %s".format(it.name(), result1, result2))
        errors.forEach { err -> error(err) }
    }
}

fun interface Part {
    fun solve(input: String): Any
}

abstract class Day(name: String?) {

    private val name: String?

    init {
        this.name = name
    }

    constructor() : this(null) {
    }

    fun name(): String {
        return this.name ?: this.javaClass.simpleName
    }

    abstract fun part1(input: String): Any
    abstract fun part2(input: String): Any
}

// List all solutions of all days here
val solutions = listOf<Day>(
    Day1()
)

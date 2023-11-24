package de.kevinboeckler.aoc23

import java.io.File

fun main() {
    solutions.forEach {
        val input = File("src/main/resources/%s.txt".format(it.name)).readText()
        println("%s: %s, %s".format(it.name, it.part1.solve(input), it.part2.solve(input)))
    }
}

fun interface Part {
    fun solve(input: String): Any
}

data class Day(val name: String, val part1: Part, val part2: Part) {
}

// List all solutions of all days here
val solutions = listOf(
        Day("Day1", day1_part1, day1_part2)
)

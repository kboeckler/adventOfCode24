package de.kevinboeckler.aoc23

import java.math.BigInteger

class Day8 : Day() {

    override fun part1(input: String): Any {
        return runStepsMatching(input, "AAA".toRegex(), "ZZZ".toRegex())
    }

    override fun part2(input: String): Any {
        return runStepsMatching(input, "..A".toRegex(), "..Z".toRegex())
    }

    private fun runStepsMatching(
        input: String,
        startMatch: Regex,
        endMatch: Regex
    ): BigInteger {
        val instructions = input.lines()[0].replace("L", "0").replace("R", "1").map { "$it".toInt() }
        val intersections = "(.+) = \\((.+), (.+)\\)".toRegex().findAll(input).map { it.groupValues }
            .map { Intersection(it[1], listOf(it[2], it[3])) }.groupBy { it.from }.mapValues { it.value[0] }
        return intersections.map { it.value.from }.filter { startMatch.matches(it) }
            .map { calculateSteps(it, endMatch, instructions, intersections) }
            .run { lcm(this) }
    }

    private fun calculateSteps(
        start: String,
        endMatch: Regex,
        instructions: List<Int>,
        intersections: Map<String, Intersection>
    ): Int {
        var steps = 0
        var current = start
        while (!current.matches(endMatch)) {
            val instruction = instructions[steps % instructions.size]
            current = intersections[current]!!.target[instruction]
            steps++
        }
        return steps
    }

    data class Intersection(val from: String, val target: List<String>)

}

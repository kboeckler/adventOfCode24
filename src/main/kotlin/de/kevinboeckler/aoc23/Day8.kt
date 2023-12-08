package de.kevinboeckler.aoc23

class Day8 : Day() {
    override fun part1(input: String): Any {
        val rlToIndex = mapOf('L' to 0, 'R' to 1)
        val instructions = input.lines()[0].map { rlToIndex[it]!! }
        val intersections = "(.+) = \\((.+), (.+)\\)".toRegex().findAll(input).map { it.groupValues }
            .map { Intersection(it[1], listOf(it[2], it[3])) }.groupBy { it.from }.mapValues { it.value[0] }
        return calculateSteps("AAA", "ZZZ".toRegex(), instructions, intersections)
    }

    override fun part2(input: String): Any {
        val rlToIndex = mapOf('L' to 0, 'R' to 1)
        val instructions = input.lines()[0].map { rlToIndex[it]!! }
        val intersections = "(.+) = \\((.+), (.+)\\)".toRegex().findAll(input).map { it.groupValues }
            .map { Intersection(it[1], listOf(it[2], it[3])) }.groupBy { it.from }.mapValues { it.value[0] }
        return intersections.map { it.value.from }.filter { it.endsWith("A") }
            .map { calculateSteps(it, "..Z".toRegex(), instructions, intersections) }
            .joinToString(",") { "$it" }.run { "lcm($this)" }
    }

    private fun calculateSteps(
        start: String,
        endCheck: Regex,
        instructions: List<Int>,
        intersections: Map<String, Intersection>
    ): Int {
        var steps = 0
        var current = start
        while (!current.matches(endCheck)) {
            val instruction = instructions[steps % instructions.size]
            current = intersections[current]!!.target[instruction]
            steps++
        }
        return steps
    }

    data class Intersection(val from: String, val target: List<String>)

}

package de.kevinboeckler.aoc23

class Day8 : Day() {
    override fun part1(input: String): Any {
        val rlToIndex = mapOf('L' to 0, 'R' to 1)
        val instructions = input.lines()[0].map { rlToIndex[it]!! }
        val intersections = "(.+) = \\((.+), (.+)\\)".toRegex().findAll(input).map { it.groupValues }
            .map { Intersection(it[1], listOf(it[2], it[3])) }.groupBy { it.from }.mapValues { it.value[0] }
        var steps = 0
        var current = "AAA"
        while (current != "ZZZ") {
            val instruction = instructions[steps % instructions.size]
            current = intersections[current]!!.target[instruction]
            steps++
        }
        return steps
    }

    override fun part2(input: String): Any {
        if (true) {
            // TODO too slow here
            return "?"
        }
        val rlToIndex = mapOf('L' to 0, 'R' to 1)
        val instructions = input.lines()[0].map { rlToIndex[it]!! }
        val intersections = "(.+) = \\((.+), (.+)\\)".toRegex().findAll(input).map { it.groupValues }
            .map { Intersection(it[1], listOf(it[2], it[3])) }.groupBy { it.from }.mapValues { it.value[0] }
        var steps = 0
        val endVertices = intersections.flatMap { it.value.target }.filter { it.endsWith("Z") }
        var current = intersections.map { it.value.from }.filter { it.endsWith("A") }
        while (current.any { !endVertices.contains(it) }) {
            val instruction = instructions[steps % instructions.size]
            current = current.map { intersections[it]!!.target[instruction] }
            steps++
        }
        return steps
    }

    data class Intersection(val from: String, val target: List<String>)
}
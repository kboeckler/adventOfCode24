package de.kevinboeckler.aoc23

class Day15 : Day() {
    override fun part1(input: String): Any {
        return input.split(",").sumOf { hashOf(it) }
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    private fun hashOf(word: String): Int {
        var value = 0
        word.forEach {
            value += it.code
            value *= 17
            value %= 256

        }
        return value
    }
}
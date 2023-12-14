package de.kevinboeckler.aoc23

class Day13 : Day() {
    override fun part1(input: String): Any {
        val patterns = input.split("\n\n")
        val scoreLeft = patterns.mapNotNull { pattern ->
            (0..pattern.indexOf('\n') - 2).filter { splitIndex ->
                pattern.lines().all { isVerticalSplit(splitIndex, it) }
            }.firstOrNull()
        }.onEachIndexed { i, split -> println("i $i has vertical split $split") }.sumOf { it + 1 }
        val patternsTransposed = patterns.map {
            transpose(it.lines()).joinToString("\n")
        }
        val scoreAbove = patternsTransposed.mapNotNull { pattern ->
            (0..pattern.indexOf('\n') - 2).filter { splitIndex ->
                pattern.lines().all { isVerticalSplit(splitIndex, it) }
            }.firstOrNull()
        }.onEachIndexed { i, split -> println("i $i has horizontal split $split") }.sumOf { (it + 1) * 100 }
        return scoreLeft + scoreAbove
    }

    override fun part2(input: String): Any {
        return "?"
    }

    private fun isVerticalSplit(splitIndex: Int, row: String): Boolean {
        val first = row.substring(0, splitIndex + 1).reversed()
        val second = row.substring(splitIndex + 1)
        return first.startsWith(second) || second.startsWith(first)
    }

}

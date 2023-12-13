package de.kevinboeckler.aoc23

class Day13 : Day() {
    override fun part1(input: String): Any {
        val patterns = input.split("\n\n")
        patterns.map {
            it.lines().asSequence().map { row -> findVerticalSplits(row) }.reduce(this::mergeVerticalSplits)
                .firstOrNull()
        }.forEachIndexed { i, split -> println("i $i has vertical split $split") }
        patterns.map {
            transpose(it.lines()).map { row -> findVerticalSplits(row) }.reduce(this::mergeVerticalSplits).firstOrNull()
        }.forEachIndexed { i, split -> println("i $i has horizontal split $split") }
        return "?"
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    private fun findVerticalSplits(row: String): List<Int> {
        val inverse = row.reversed()
        return (1..<inverse.length - 1).map { it to inverse.substring(it) }
            .filter { row.startsWith(it.second) }
            .map { row.length - it.first - 1 }
    }

    private fun mergeVerticalSplits(one: List<Int>, another: List<Int>): List<Int> {
        return one.plus(another).filterNot { one.contains(it) && another.contains(it) }
    }

    private fun transpose(rows: List<String>): List<String> {
        return (0..<rows[0].length).map { y ->
            (0..<rows.size).mapNotNull { x ->
                rows[x][y]
            }.joinToString("")
        }.toList()
    }
}
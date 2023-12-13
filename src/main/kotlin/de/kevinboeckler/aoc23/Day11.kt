package de.kevinboeckler.aoc23

import kotlin.math.abs

class Day11 : Day() {
    override fun part1(input: String): Any {
        val universe = expandUniverse(input)
        val galaxies =
            universe.flatMapIndexed { y, row -> row.mapIndexedNotNull { x, c -> if (c == '#') x to y else null } }
        val shortestPaths = galaxies.flatMap {
            galaxies.filterNot { g -> g == it }.map { g ->
                Path(it, g, abs(g.first - it.first) + abs(g.second - it.second))
            }
        }
        return shortestPaths.sumOf { it.length } / 2
    }

    override fun part2(input: String): Any {
        return "?"
    }

    data class Path(val from: Pair<Int, Int>, val to: Pair<Int, Int>, val length: Int)

    private fun expandUniverse(input: String): List<String> {
        val rowsUnexpanded =
            input.replace("\\n(\\.+)\\n".toRegex()) { "\n" + it.groupValues[1] + "\n" + it.groupValues[1] + "\n" }
                .lines().filterNot { it.isBlank() }
        val expandingCols =
            rowsUnexpanded.reduce { a, b -> mergeUniverseRows(a, b) }
                .mapIndexedNotNull { x, c -> if (c == '.') x else null }
        return rowsUnexpanded.map { fillWithDotAtIndices(it, expandingCols) }
    }

    private fun mergeUniverseRows(one: String, another: String): String {
        return one.mapIndexed { i, c -> if ((c == '#') || another[i] == '#') '#' else '.' }.joinToString("")
    }

    private fun fillWithDotAtIndices(target: String, expandingCols: List<Int>): String {
        var expandedString = ""
        var startIndex = 0
        for (expandIndex in expandingCols) {
            for (i in startIndex..expandIndex) {
                val original = target[i]
                expandedString = "$expandedString$original"
            }
            startIndex = expandIndex + 1
            expandedString = "$expandedString."
        }
        for (i in startIndex..<target.length) {
            val original = target[i]
            expandedString = "$expandedString$original"
        }
        return expandedString
    }
}

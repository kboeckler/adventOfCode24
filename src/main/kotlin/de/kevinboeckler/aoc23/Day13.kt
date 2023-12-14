package de.kevinboeckler.aoc23

class Day13 : Day() {
    override fun part1(input: String): Any {
        return solveForMirrorWithDifferences(input, 0)
    }

    override fun part2(input: String): Any {
        return solveForMirrorWithDifferences(input, 1)
    }

    private fun solveForMirrorWithDifferences(input: String, differences: Int): Int {
        val patterns = input.split("\n\n")
        val patternsTransposed = patterns.map {
            transpose(it.lines()).joinToString("\n")
        }
        val scoreLeft =
            patterns.mapNotNull { pattern -> findVerticalMirrorIndex(pattern, differences) }.sumOf { it + 1 }
        val scoreAbove =
            patternsTransposed.mapNotNull { pattern -> findVerticalMirrorIndex(pattern, differences) }
                .sumOf { (it + 1) * 100 }
        return scoreLeft + scoreAbove
    }

    private fun findVerticalMirrorIndex(pattern: String, differences: Int): Int? {
        return (0..pattern.indexOf('\n') - 2).firstOrNull { splitIndex ->
            pattern.lines().sumOf { differencesByMirroringAt(splitIndex, it) } == differences
        }
    }

    private fun differencesByMirroringAt(splitIndex: Int, row: String): Int {
        val first = row.substring(0, splitIndex + 1).reversed()
        val second = row.substring(splitIndex + 1)
        if (first.length <= second.length) {
            val secondFitsFirst = second.substring(0, first.length)
            return secondFitsFirst.filterIndexed { i, c -> first[i] != c }.count()
        } else {
            val firstFitsSecond = first.substring(0, second.length)
            return firstFitsSecond.filterIndexed { i, c -> second[i] != c }.count()
        }
    }

}

package com.github.kboeckler.aoc24

import kotlin.math.abs

class Day2 : Day {
    override fun part1(input: String): Any {
        return input.lines().map { it.split(" ") }
            .map { it.map { innerIt -> innerIt.toInt() } }.count { indexOfWrongLevel(it) == -1 }
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    private fun indexOfWrongLevel(levels: List<Int>): Int {
        val diffs = (0..levels.size - 2).map { levels[it] - levels[it + 1] }
        val firstLevelNotHavingSameNorm =
            (0..diffs.size - 2).indexOfFirst { diffs[it].norm() != diffs[it + 1].norm() }
        val firstLevelNotDifferingInRange = diffs.indexOfFirst { abs(it) !in 1..3 }
        return if (firstLevelNotHavingSameNorm != -1) {
            firstLevelNotHavingSameNorm
        } else if (firstLevelNotDifferingInRange != -1) {
            firstLevelNotDifferingInRange
        } else -1
    }

    private fun Int.norm(): Int {
        return if (this < 0) {
            -1
        } else if (this > 0) {
            1
        } else 0
    }
}
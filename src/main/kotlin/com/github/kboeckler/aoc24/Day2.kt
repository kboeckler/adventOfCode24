package com.github.kboeckler.aoc24

import kotlin.math.abs

class Day2 : Day {
    override fun part1(input: String): Any {
        return input.lines().map { it.split(" ") }
            .map { it.map { innerIt -> innerIt.toInt() } }.count(::noWrongLevels)
    }

    override fun part2(input: String): Any {
        return input.lines().map { it.split(" ") }
            .map { it.map { innerIt -> innerIt.toInt() } }.count(::atMostOneWrongLevel)
    }

    private fun noWrongLevels(levels: List<Int>) = indexOfWrongLevel(levels) == -1

    private fun atMostOneWrongLevel(levels: List<Int>): Boolean {
        val firstWrongIndex = indexOfWrongLevel(levels)
        if (firstWrongIndex == -1) {
            return true
        }
        val firstRetry = levels.subList(0, firstWrongIndex - 1) +
                levels.subList(
                    firstWrongIndex,
                    levels.size
                )
        val firstRetryWrongIndex = indexOfWrongLevel(firstRetry)
        if (firstRetryWrongIndex == -1) {
            return true
        }
        val secondTry = levels.subList(0, firstWrongIndex) + if (firstWrongIndex + 1 <= levels.size - 1) levels.subList(
            firstWrongIndex + 1,
            levels.size
        ) else emptyList()
        val secondTryWrongIndex = indexOfWrongLevel(secondTry)
        if (secondTryWrongIndex == -1) {
            return true
        }
        return false
    }

    fun indexOfWrongLevel(levels: List<Int>): Int {
        val diffs = (0..levels.size - 2).map { levels[it] - levels[it + 1] }
        val firstLevelNotHavingSameNorm =
            (1..diffs.size - 1).firstOrNull { diffs[it].norm() != diffs[it - 1].norm() }
        val firstLevelNotDifferingInRange = diffs.indexOfFirst { abs(it) !in 1..3 }
        return if (firstLevelNotHavingSameNorm != null) {
            firstLevelNotHavingSameNorm + 1
        } else if (firstLevelNotDifferingInRange != -1) {
            firstLevelNotDifferingInRange + 1
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
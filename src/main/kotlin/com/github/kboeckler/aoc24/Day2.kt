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
        if (noWrongLevels(levels)) {
            return true
        }
        return levels.indices.any {
            noWrongLevels(levels.withoutItemAt(it))
        }
    }

    fun indexOfWrongLevel(levels: List<Int>): Int {
        val diffs = (0..levels.size - 2).map { levels[it] - levels[it + 1] }
        val firstLevelNotHavingSameNorm =
            (1..<diffs.size).firstOrNull { diffs[it].norm() != diffs[it - 1].norm() }
        val firstLevelNotDifferingInRange = diffs.indexOfFirst { abs(it) !in 1..3 }
        return if (firstLevelNotHavingSameNorm != null) {
            firstLevelNotHavingSameNorm + 1
        } else if (firstLevelNotDifferingInRange != -1) {
            firstLevelNotDifferingInRange + 1
        } else -1
    }

    private fun <T> List<T>.withoutItemAt(itemIndex: Int): List<T> {
        return if (itemIndex < 0) emptyList() else this.subList(
            0, itemIndex
        ) + if (itemIndex + 1 <= this.size - 1) this.subList(
            itemIndex + 1,
            this.size
        ) else emptyList()
    }

    private fun Int.norm(): Int {
        return if (this < 0) {
            -1
        } else if (this > 0) {
            1
        } else 0
    }
}
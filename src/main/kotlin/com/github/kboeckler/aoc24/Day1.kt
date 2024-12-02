package com.github.kboeckler.aoc24

import kotlin.math.abs

class Day1 : Day {
    override fun part1(input: String): Any {
        val allIds = input.lines().flatMap(::splitToInteger)
        val firstIds = allIds.evenItems().sorted()
        val secondIds = allIds.unevenItems().sorted()
        return firstIds.mapIndexed { idx, firstId ->
            firstId - secondIds[idx]
        }
            .map(::abs)
            .sum()
    }

    override fun part2(input: String): Any {
        val allIds = input.lines().flatMap(::splitToInteger)
        val firstIds = allIds.evenItems()
        val secondIds = allIds.unevenItems()
        return firstIds.sumOf { firstId ->
            firstId * secondIds.count { secondId ->
                firstId == secondId
            }
        }
    }

    private fun splitToInteger(line: String): Iterable<Int> {
        val res = Regex("(\\d+)").findAll(line)
        return res.map { it.groupValues[1].toInt() }.toList()
    }

    private fun <T> List<T>.evenItems(): List<T> {
        return this.filterIndexed(filterIndexMod2<T>(0))
    }

    private fun <T> List<T>.unevenItems(): List<T> {
        return this.filterIndexed(filterIndexMod2<T>(1))
    }

    private fun <T> filterIndexMod2(mod: Int): (index: Int, T) -> Boolean {
        return { idx, _ ->
            idx % 2 == mod
        }
    }
}

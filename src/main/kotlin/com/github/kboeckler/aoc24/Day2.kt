package com.github.kboeckler.aoc24

import kotlin.math.abs

class Day2 : Day {
    override fun part1(input: String): Any? {
        return input.lines().map { it.split(" ") }
            .map { it.map { innerIt -> innerIt.toInt() } }
            .map { line ->
                (0..line.size - 2).map { line[it] - line[it + 1] }
            }
            .filter { diffs ->
                (0..diffs.size - 2).all { diffs[it].norm() == diffs[it + 1].norm() }
            }.filter { diffs ->
                diffs.all { abs(it) in 1..3 }
            }.count()
    }

    override fun part2(input: String): Any? {
        TODO("Not yet implemented")
    }

    private fun Int.norm(): Int {
        return if (this < 0) {
            -1
        } else if (this > 0) {
            1
        } else 0
    }
}
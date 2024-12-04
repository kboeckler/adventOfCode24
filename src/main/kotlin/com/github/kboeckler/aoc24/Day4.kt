package com.github.kboeckler.aoc24

class Day4 : Day {
    override fun part1(input: String): Any {
        TODO("Not yet implemented")
        transpose(input.lines()).flatMap { tLine ->
            listOf(tLine, tLine.reversed())
        }.forEach { println(it) }
        input.lines().flatMap { line ->
            listOf(line, line.reversed())
        }.forEach { println(it) }
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    private fun diagonals(lines: List<String>): List<String> {
        TODO("Not yet implemented")
    }
}
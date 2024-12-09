package com.github.kboeckler.aoc24

class Day9 : Day {
    override fun part1(input: String): Any {
        return input.let { line ->
            val ids = (0 until line.length / 2 + 1).map { "$it" }.toList()
            val dots = line.map { "." }.toList()
            val symbols = ids.zip(dots).flatMap { listOf(it.first, it.second) }
            val amounts = line.map { "$it".toInt() }
            val expanded = amounts.zip(symbols).map { assignment ->
                (0 until assignment.first).map { assignment.second }.joinToString("")
            }.joinToString("")
            println(expanded)
            expanded.length
        }
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }
}